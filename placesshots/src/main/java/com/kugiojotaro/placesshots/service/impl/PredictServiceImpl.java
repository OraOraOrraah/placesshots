package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.constant.PlaceShotsConstant;
import com.kugiojotaro.placesshots.dao.FixtureDao;
import com.kugiojotaro.placesshots.dao.PredictChampionDao;
import com.kugiojotaro.placesshots.dao.PredictDao;
import com.kugiojotaro.placesshots.dao.TeamDao;
import com.kugiojotaro.placesshots.dao.UserDao;
import com.kugiojotaro.placesshots.dto.PredictChampionDisplayDto;
import com.kugiojotaro.placesshots.dto.PredictChampionDto;
import com.kugiojotaro.placesshots.dto.PredictDto;
import com.kugiojotaro.placesshots.dto.PredictResultDetailDto;
import com.kugiojotaro.placesshots.dto.PredictResultDto;
import com.kugiojotaro.placesshots.dto.UserPointDto;
import com.kugiojotaro.placesshots.entity.Fixture;
import com.kugiojotaro.placesshots.entity.Predict;
import com.kugiojotaro.placesshots.entity.PredictChampion;
import com.kugiojotaro.placesshots.entity.Team;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.mapper.PredictChampionMapper;
import com.kugiojotaro.placesshots.mapper.PredictMapper;
import com.kugiojotaro.placesshots.service.PredictService;
import com.kugiojotaro.placesshots.util.Helper;
import com.kugiojotaro.placesshots.util.PredictHelper;

@Service
public class PredictServiceImpl implements PredictService {

	private static final Logger LOGGER = Logger.getLogger(PredictServiceImpl.class);

	@Autowired
	private PredictDao predictDao;
	
	@Autowired
	private PredictChampionDao predictChampionDao;
	
	@Autowired
	private FixtureDao fixtureDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private PredictMapper predictMapper;
	
	@Autowired
	private PredictChampionMapper predictChampionMapper;
	
	@Override
	public Boolean create(PredictDto predictDto) {
		LOGGER.info(" create");
		
		try {
			Predict predict = predictMapper.toPersistenceBean(predictDto);
			predictDao.save(predict);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return true;
	}
	
	@Override
	public Boolean predict(List<PredictDto> listPredictDto) {
		LOGGER.info(" predict");
		
		try {
			//
			String user = "";
			Short week = null;
			for (PredictDto predictDto : listPredictDto) {
				user = predictDto.getUser();
				week = Helper.string2Short(predictDto.getWeek());
				
				LOGGER.info(" user: " + user);
				LOGGER.info(" week: " + week);
				
				break;
			}
			
			predictDao.delete(predictDao.findByUserAndWeek(user, week));
			
			//
			for (PredictDto predictDto : listPredictDto) {
				LOGGER.info(" fixtureId: " + predictDto.getFixtureId());
				LOGGER.info(" homeScore: " + predictDto.getHomeScore());
				LOGGER.info(" awayScore: " + predictDto.getAwayScore());
				
				Predict predict = predictMapper.toPersistenceBean(predictDto);
				//predict.setUser(userDao.findOne(predictDto.getUser()));
				predict.setFixture(Helper.string2Long(predictDto.getFixtureId()));
				predict.setCreateDate(new Date());
				predictDao.save(predict);
				
				LOGGER.info(" save: " + predict.getId());
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return true;
	}

	/*
	@Override
	public Boolean update(PredictDto predictDto) {
		LOGGER.info(" update");
		
		return true;
	}

	@Override
	public Boolean delete(Long id) {
		LOGGER.info(" delete");
		
		return true;
	}
	*/

	@Override
	public List<PredictDto> findByUser(String username) {
		LOGGER.info(" findByUser");
		
		List<PredictDto> result = new ArrayList<PredictDto>();
		
		try {
			List<Predict> listPredict = predictDao.findByUser(username);
			for (Predict predict : listPredict) {
				PredictDto predictDto = new PredictDto();
				predictDto = predictMapper.toDtoBean(predict);
				predictDto.setFixtureId((predict.getFixture() + ""));
				
				result.add(predictDto);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<PredictDto> findByWeek(Short week) {
		LOGGER.info(" findByUsername");
		
		List<PredictDto> result = new ArrayList<PredictDto>();
		
		try {
			List<Predict> listPredict = predictDao.findByWeek(week);
			for (Predict predict : listPredict) {
				PredictDto predictDto = new PredictDto();
				predictDto = predictMapper.toDtoBean(predict);
				predictDto.setFixtureId((predict.getFixture() + ""));
				
				result.add(predictDto);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<PredictDto> findByUserAndWeek(String username, Short week) {
		LOGGER.info(" findByUserAndWeek");
		
		List<PredictDto> result = new ArrayList<PredictDto>();
		
		try {
			List<Predict> listPredict = predictDao.findByUserAndWeek(username, week);
			for (Predict predict : listPredict) {
				PredictDto predictDto = new PredictDto();
				predictDto = predictMapper.toDtoBean(predict);
				predictDto.setFixtureId((predict.getFixture() + ""));
				
				result.add(predictDto);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public List<PredictResultDto> weeklyResult(Short week) {
		LOGGER.info(" weeklyResult: " + week);
		
		List<PredictResultDto> result = new ArrayList<PredictResultDto>();
		
		try {
			//
			List<Fixture> listFixture =  fixtureDao.findByWeek(week);
			
			//
			Map<String, Map<Long, Predict>> mapUserPredict = new HashMap<String, Map<Long, Predict>>();
			List<Predict> listPredict = predictDao.findByWeekOrderByUserAsc(week);
			for (Predict predict : listPredict) {
				if (mapUserPredict.containsKey(predict.getUser()) == false) {
					mapUserPredict.put(predict.getUser(), new HashMap<Long, Predict>());
				}
				
				Map<Long, Predict> mapPredict = mapUserPredict.get((predict.getUser()));
				mapPredict.put(predict.getFixture(), predict);
				
				mapUserPredict.put(predict.getUser(), mapPredict);
			}
			
			//
			/*
			PredictResultDto header = new PredictResultDto();
			header.setPoint(0);
			for (Fixture fixture : listFixture) {
				PredictResultDetailDto predictResultDetailDto = new PredictResultDetailDto();
				predictResultDetailDto.setNo(fixture.getHome().getShortTitle() + "-" + fixture.getAway().getShortTitle() + "<br/>" + Helper.nullObj2Blank(fixture.getHomeScore()) + " - " + Helper.nullObj2Blank(fixture.getAwayScore()));
				header.getListPredictResultDetailDto().add(predictResultDetailDto);
			}
			result.add(header);
			*/
			
			//
			List<User> listUser = userDao.findAll();
			for (User user : listUser) {
				PredictResultDto predictResultDto = new PredictResultDto();
				predictResultDto.setUsername(user.getUsername());
				predictResultDto.setPoint(0);
				
				List<PredictResultDetailDto> listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
				for (Fixture fixture : listFixture) {
					PredictResultDetailDto predictResultDetailDto = new PredictResultDetailDto();
					predictResultDetailDto.setPredictHomeScore("");
					predictResultDetailDto.setPredictAwayScore("");
					predictResultDetailDto.setPoint(0);
					
					if (mapUserPredict.get(user.getUsername()) != null) {
						Map<Long, Predict> mapPredict = mapUserPredict.get((user.getUsername()));
						if (mapPredict.get(fixture.getId()) != null) {
							Predict predict = mapPredict.get(fixture.getId());
							predictResultDetailDto.setPredictHomeScore(Helper.null2Blank(predict.getHomeScore() + ""));
							predictResultDetailDto.setPredictAwayScore(Helper.null2Blank(predict.getAwayScore() + ""));
							if (fixture.getHomeScore() != null && fixture.getAwayScore() != null) {
								predictResultDetailDto.setPoint(Helper.string2Integer(PredictHelper.calculatePoint(fixture, predict) + ""));
								predictResultDto.setPoint(predictResultDto.getPoint() + predictResultDetailDto.getPoint());
							}
							else {
								predictResultDetailDto.setPoint(0);
							}
							
						}
					}
					
					listPredictResultDetailDto.add(predictResultDetailDto);
				}
				
				predictResultDto.setListPredictResultDetailDto(listPredictResultDetailDto);
				
				result.add(predictResultDto);
			}
			
			LOGGER.info(" weeklyResult Success");
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<PredictResultDto> result(String username) {
		LOGGER.info(" result: " + username);
		
		List<PredictResultDto> result = new ArrayList<PredictResultDto>();
		
		try {
			//
			List<Fixture> listFixture =  fixtureDao.findByLeague(PlaceShotsConstant.EURO_2016);
			
			//
			Map<String, Map<Long, Predict>> mapUserPredict = new HashMap<String, Map<Long, Predict>>();
			List<Predict> listPredict = predictDao.findByUser(username);
			for (Predict predict : listPredict) {
				if (mapUserPredict.containsKey(predict.getUser()) == false) {
					mapUserPredict.put(predict.getUser(), new HashMap<Long, Predict>());
				}
				
				Map<Long, Predict> mapPredict = mapUserPredict.get((predict.getUser()));
				mapPredict.put(predict.getFixture(), predict);
				
				mapUserPredict.put(predict.getUser(), mapPredict);
			}
			
			PredictResultDto predictResultDto = new PredictResultDto();
			predictResultDto.setUsername(username);
			predictResultDto.setPoint(0);
				
			List<PredictResultDetailDto> listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
			for (Fixture fixture : listFixture) {
				PredictResultDetailDto predictResultDetailDto = new PredictResultDetailDto();
				predictResultDetailDto.setPredictHomeScore("");
				predictResultDetailDto.setPredictAwayScore("");
				predictResultDetailDto.setPoint(0);
					
				if (mapUserPredict.get(username) != null) {
					Map<Long, Predict> mapPredict = mapUserPredict.get((username));
					if (mapPredict.get(fixture.getId()) != null) {
						Predict predict = mapPredict.get(fixture.getId());
						predictResultDetailDto.setPredictHomeScore(Helper.null2Blank(predict.getHomeScore() + ""));
						predictResultDetailDto.setPredictAwayScore(Helper.null2Blank(predict.getAwayScore() + ""));
						if (fixture.getHomeScore() != null && fixture.getAwayScore() != null) {
							predictResultDetailDto.setPoint(Helper.string2Integer(PredictHelper.calculatePoint(fixture, predict) + ""));
							predictResultDto.setPoint(predictResultDto.getPoint() + predictResultDetailDto.getPoint());
						}
						else {
							predictResultDetailDto.setPoint(0);
						}
						
					}
				}
				
				listPredictResultDetailDto.add(predictResultDetailDto);
			}
			
			predictResultDto.setListPredictResultDetailDto(listPredictResultDetailDto);
			
			result.add(predictResultDto);
			
			LOGGER.info(" result Success");
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<UserPointDto> standing(Short week) {
		LOGGER.info(" standing - week: " + week);
		
		List<UserPointDto> result = new ArrayList<UserPointDto>();
		
		try {
			
			int championId = 0;
			
			//
			Map<Long, Fixture> mapFixture = new HashMap<Long, Fixture>();
			List<Fixture> listFixture =  fixtureDao.findAll();
			for (Fixture fixture : listFixture) {
				mapFixture.put(fixture.getId(), fixture);
				
				if (Helper.null2Blank(fixture.getRound() + "").equals("2")) {
					int homeScore = Helper.string2Integer(Helper.null2Zero(fixture.getHomeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomeExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomePenaltyScore() + ""));
					int awayScore = Helper.string2Integer(Helper.null2Zero(fixture.getAwayScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayPenaltyScore() + ""));
					LOGGER.info(" homescore: " + homeScore);
					LOGGER.info(" awayscore: " + awayScore);
					if (homeScore > awayScore) {
						championId = fixture.getHome().getId();
					}
					if (awayScore > homeScore) {
						championId = fixture.getAway().getId();
					}
					LOGGER.info(" championId: " + championId);
				}
			}
			
			//
			Map<String, PredictChampion> mapPredictChampion = new HashMap<String, PredictChampion>();
			List<PredictChampion> listPredictChampion = predictChampionDao.findAll();
			for (PredictChampion predictChampion : listPredictChampion) {
				mapPredictChampion.put(predictChampion.getUser(), predictChampion);
			}
			
			Map<String, Integer> mapUserPoint = new HashMap<String, Integer>();

			List<Predict> listPredict = predictDao.findAll(new Sort(Direction.ASC, "user"));
			for (Predict predict : listPredict) {
				if (mapUserPoint.containsKey(predict.getUser()) == false) {
					mapUserPoint.put(predict.getUser(), 0);
				}

				Fixture fixture = mapFixture.get(predict.getFixture());
				//Short point = (short) (mapUserPoint.get(predict.getUser()) + Helper.string2Short(Helper.nullObj2Zero(predict.getPoint())));
				if (fixture.getHomeScore() != null && fixture.getAwayScore() != null) {
					mapUserPoint.put(predict.getUser(), mapUserPoint.get(predict.getUser()) + PredictHelper.calculatePoint(fixture, predict));
				}
			}
			
			List<User> listUser = userDao.findAll();
			for (User user : listUser) {
				UserPointDto userPointDto = new UserPointDto();
				userPointDto.setUsername(user.getUsername());
				userPointDto.setPoint(0);
				userPointDto.setPredictCount(0);
				userPointDto.setCorrectResult(0);
				userPointDto.setCorrectResultAndScore(0);
				userPointDto.setIncorrectResult(0);
				userPointDto.setExtraPoint(99);
				
				if (mapUserPoint.get(user.getUsername()) != null) {
					userPointDto.setPoint(mapUserPoint.get(user.getUsername()));
				}
				
				for (Predict predict : listPredict) {
					//if (predict.getWeek() <= week) {
						if (predict.getUser().equals(user.getUsername())) {
							
							//
							Fixture fixture = mapFixture.get(predict.getFixture());
							
							if (fixture.getHomeScore() != null && fixture.getAwayScore() != null) {
								//
								userPointDto.setPredictCount(userPointDto.getPredictCount() + 1);
								
								//
								Short point = PredictHelper.calculatePoint(fixture, predict);
								if (point > 0) {
									if (point == 4) {
										userPointDto.setCorrectResultAndScore(userPointDto.getCorrectResultAndScore() + 1);
									}
									else {
										userPointDto.setCorrectResult(userPointDto.getCorrectResult() + 1);
									}
								}
								else {
									userPointDto.setIncorrectResult(userPointDto.getIncorrectResult() + 1);
								}
							}
						}
					//}
				}
				
				//
				PredictChampion p = mapPredictChampion.get(user.getUsername());
				if (p != null) {
					
					userPointDto.setExtraPointFlag("Y");
					
					if (championId > 0) {
						if (p.getTeamId().intValue() == championId) {
							if (p.getRound().shortValue() == 8) {
								userPointDto.setExtraPoint(16);
							}
							if (p.getRound().shortValue() == 4) {
								userPointDto.setExtraPoint(8);
							}
							if (p.getRound().shortValue() == 2) {
								userPointDto.setExtraPoint(4);
							}
						}
						else {
							userPointDto.setExtraPoint(0);
						}
					}
					else {
						for (Fixture fixture : listFixture) {
							//if (fixture.getRound() != null && fixture.getRound().shortValue() == p.getRound().shortValue()) {
							if (fixture.getRound() != null) {
								if (fixture.getHome().getId().intValue() == p.getTeamId().intValue() || fixture.getAway().getId().intValue() == p.getTeamId().intValue()) {
									int homeScore = Helper.string2Integer(Helper.null2Zero(fixture.getHomeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomeExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomePenaltyScore() + ""));
									int awayScore = Helper.string2Integer(Helper.null2Zero(fixture.getAwayScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayPenaltyScore() + ""));
									
									if (fixture.getHome().getId().intValue() == p.getTeamId().intValue()) {
										if (homeScore < awayScore) {
											userPointDto.setExtraPoint(0);
											break;
										}
									}
									if (fixture.getAway().getId().intValue() == p.getTeamId().intValue()) {
										if (homeScore > awayScore) {
											userPointDto.setExtraPoint(0);
											break;
										}
									}
									//break;
								}
							}
						}
					}
				}
				
				userPointDto.setTotalPoint(userPointDto.getPoint() + (userPointDto.getExtraPoint() == 99 ? 0 : userPointDto.getExtraPoint()));
				
				result.add(userPointDto);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public Boolean updatePoint(Short week) {
		LOGGER.info(" updatePoint (week: " + week + ")");
		
		Boolean result = false;
		
		try {
			//
			Map<Long, Fixture> mapFixture = new HashMap<Long, Fixture>();
			List<Fixture> listFixture =  fixtureDao.findByWeek(week);
			for (Fixture fixture : listFixture) {
				mapFixture.put(fixture.getId(), fixture);
			}
			
			//
			List<Predict> listPredictUpdate = new ArrayList<Predict>();
			
			List<Predict> listPredict = predictDao.findByWeek(week);
			for (Predict predict : listPredict) {
				Fixture fixture = mapFixture.get(predict.getFixture());
				
				predict.setPoint(PredictHelper.calculatePoint(fixture, predict));
			}
			
			predictDao.save(listPredictUpdate);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public Boolean predictChampion(PredictChampionDto predictChampionDto) {
		LOGGER.info(" save predict champion");
		
		Boolean result = false;
		
		try {
			PredictChampion predictChampionToDelete = predictChampionDao.findByUser(predictChampionDto.getUser());
			if (predictChampionToDelete != null) {
				predictChampionDao.delete(predictChampionToDelete);
			}
	
			if (predictChampionDto.getTeamId() != null && !predictChampionDto.getTeamId().equals("")) {
				PredictChampion predictChampion = predictChampionMapper.toPersistenceBean(predictChampionDto);
				predictChampion.setCreateDate(new Date());
				predictChampionDao.save(predictChampion);
					
				LOGGER.info(" save success: " + predictChampion.getId());
			}

			result = true;
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public PredictChampionDto findPredictChampionByUser(String username) {
		LOGGER.info(" findPredictChampionByUser");
		
		PredictChampionDto predictChampionDto = null;
		
		try {
			PredictChampion predictChampion = predictChampionDao.findByUser(username);
			if (predictChampion != null) {
				predictChampionDto = predictChampionMapper.toDtoBean(predictChampion);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return predictChampionDto;
	}
	
	@Override
	public List<PredictChampionDisplayDto> resultPredictChampion() {
		LOGGER.info(" result predict champion");
		
		List<PredictChampionDisplayDto> result = new ArrayList<PredictChampionDisplayDto>();
		
		try {
			//
			List<PredictChampion> listPredictChampion = predictChampionDao.findAll();
			Map<String, PredictChampion> mapPredictChampion = new HashMap<String, PredictChampion>();
			for (PredictChampion prdictChampion : listPredictChampion) {
				mapPredictChampion.put(prdictChampion.getUser(), prdictChampion);
			}
			
			//
			List<Team> listTeam = teamDao.findByLeague(PlaceShotsConstant.EURO_2016);
			Map<Integer, Team> mapTeam = new HashMap<Integer, Team>();
			for (Team team : listTeam) {
				mapTeam.put(team.getId(), team);
			}
			
			//
			int championId = 0;
			List<Fixture> listFixture =  fixtureDao.findAll();
			for (Fixture fixture : listFixture) {
				if (Helper.null2Blank(fixture.getRound() + "").equals("2")) {
					int homeScore = Helper.string2Integer(Helper.null2Zero(fixture.getHomeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomeExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomePenaltyScore() + ""));
					int awayScore = Helper.string2Integer(Helper.null2Zero(fixture.getAwayScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayPenaltyScore() + ""));
					LOGGER.info(" homescore: " + homeScore);
					LOGGER.info(" awayscore: " + awayScore);
					if (homeScore > awayScore) {
						championId = fixture.getHome().getId();
					}
					if (awayScore > homeScore) {
						championId = fixture.getAway().getId();
					}
					LOGGER.info(" championId: " + championId);
				}
			}
			
			//
			List<User> listUser = userDao.findAll();
			for (User user : listUser) {
				PredictChampionDisplayDto predictChampionDisplayDto = new PredictChampionDisplayDto();
				predictChampionDisplayDto.setUser(user.getUsername());
				predictChampionDisplayDto.setPoint("");
				
				if (mapPredictChampion.get(user.getUsername()) != null) {	
					PredictChampion predictChampion = mapPredictChampion.get(user.getUsername());
					predictChampionDisplayDto.setTeamId(predictChampion.getTeamId() + "");
					predictChampionDisplayDto.setTeamTitle(mapTeam.get(predictChampion.getTeamId()).getTitle());
					predictChampionDisplayDto.setTeamShortTitle(mapTeam.get(predictChampion.getTeamId()).getShortTitle());
					predictChampionDisplayDto.setRound(predictChampion.getRound() + "");
					
					if (championId > 0) {
						if (predictChampion.getTeamId().intValue() == championId) {
							if (predictChampion.getRound().shortValue() == 8) {
								predictChampionDisplayDto.setPoint("16");
							}
							else if (predictChampion.getRound().shortValue() == 4) {
								predictChampionDisplayDto.setPoint("8");
							}
							else if (predictChampion.getRound().shortValue() == 2) {
								predictChampionDisplayDto.setPoint("4");
							}
						}
						else {
							predictChampionDisplayDto.setPoint("0");
						}
					}
					else {
						for (Fixture fixture : listFixture) {
							//if (fixture.getRound() != null && fixture.getRound().shortValue() == predictChampion.getRound().shortValue()) {
							if (fixture.getRound() != null) {
								if (fixture.getHome().getId().intValue() == predictChampion.getTeamId().intValue() || fixture.getAway().getId().intValue() == predictChampion.getTeamId().intValue()) {
									int homeScore = Helper.string2Integer(Helper.null2Zero(fixture.getHomeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomeExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomePenaltyScore() + ""));
									int awayScore = Helper.string2Integer(Helper.null2Zero(fixture.getAwayScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayPenaltyScore() + ""));
									
									if (fixture.getHome().getId().intValue() == predictChampion.getTeamId().intValue()) {
										if (homeScore < awayScore) {
											predictChampionDisplayDto.setPoint("0");
											break;
										}
									}
									if (fixture.getAway().getId().intValue() == predictChampion.getTeamId().intValue()) {
										if (homeScore > awayScore) {
											predictChampionDisplayDto.setPoint("0");
											break;
										}
									}
									
								}
							}
						}
						
						/*
						if (predictChampionDisplayDto.getPoint().equals("")) {
							for (Fixture fixture : listFixture) {
								if (fixture.getHome().getId().intValue() == predictChampion.getTeamId().intValue() || fixture.getAway().getId().intValue() == predictChampion.getTeamId().intValue()) {
									int homeScore = Helper.string2Integer(Helper.null2Zero(fixture.getHomeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomeExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomePenaltyScore() + ""));
									int awayScore = Helper.string2Integer(Helper.null2Zero(fixture.getAwayScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayPenaltyScore() + ""));
									
									if (fixture.getHome().getId().intValue() == predictChampion.getTeamId().intValue()) {
										if (homeScore < awayScore) {
											predictChampionDisplayDto.setPoint("0");
										}
									}
									if (fixture.getAway().getId().intValue() == predictChampion.getTeamId().intValue()) {
										if (homeScore > awayScore) {
											predictChampionDisplayDto.setPoint("0");
										}
									}
									break;
								}
							}
						}
						*/
					}
				}
				
				result.add(predictChampionDisplayDto);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}

}