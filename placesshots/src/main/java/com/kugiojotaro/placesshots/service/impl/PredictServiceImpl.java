package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dto.PredictChampionDisplayDto;
import com.kugiojotaro.placesshots.dto.PredictChampionDto;
import com.kugiojotaro.placesshots.dto.PredictDto;
import com.kugiojotaro.placesshots.dto.PredictResultDetailDto;
import com.kugiojotaro.placesshots.dto.PredictResultDto;
import com.kugiojotaro.placesshots.dto.UserPointDto;
import com.kugiojotaro.placesshots.dto.UserPredictPerformanceDto;
import com.kugiojotaro.placesshots.entity.Fixture;
import com.kugiojotaro.placesshots.entity.Predict;
import com.kugiojotaro.placesshots.entity.PredictChampion;
import com.kugiojotaro.placesshots.entity.Team;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.mapper.PredictChampionMapper;
import com.kugiojotaro.placesshots.mapper.PredictMapper;
import com.kugiojotaro.placesshots.repository.FixtureRepository;
import com.kugiojotaro.placesshots.repository.PredictChampionRepository;
import com.kugiojotaro.placesshots.repository.PredictCustomRepository;
import com.kugiojotaro.placesshots.repository.PredictRepository;
import com.kugiojotaro.placesshots.repository.TeamRepository;
import com.kugiojotaro.placesshots.repository.UserRepository;
import com.kugiojotaro.placesshots.service.PredictService;
import com.kugiojotaro.placesshots.util.Consts;
import com.kugiojotaro.placesshots.util.Helper;
import com.kugiojotaro.placesshots.util.PredictHelper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class PredictServiceImpl implements PredictService {

	@Autowired
	private PredictRepository predictRepository;
	
	@Autowired
	private PredictCustomRepository predictCustomRepository;
	
	@Autowired
	private PredictChampionRepository predictChampionRepository;
	
	@Autowired
	private FixtureRepository fixtureRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PredictMapper predictMapper;
	
	@Autowired
	private PredictChampionMapper predictChampionMapper;
	
	@Override
	public Boolean create(PredictDto predictDto) {
		log.debug(" create");
		
		try {
			Predict predict = predictMapper.toPersistenceBean(predictDto);
			predictRepository.save(predict);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return true;
	}
	
	@Override
	public Boolean predict(Integer userId, Short week, List<PredictDto> listPredictDto) {
		log.debug(" predict");
		
		try {
			predictRepository.delete(predictCustomRepository.findByUserIdAndWeek(userId, week));
			
			//
			for (PredictDto predictDto : listPredictDto) {
				log.info(" fixtureId: " + predictDto.getFixtureId());
				log.info(" homeScore: " + predictDto.getHomeScore());
				log.info(" awayScore: " + predictDto.getAwayScore());
				
				Predict predict = predictMapper.toPersistenceBean(predictDto);
				predict.setUserId(Helper.string2Integer(predictDto.getUserId()));
				Fixture f = fixtureRepository.findOne(Helper.string2Long(predictDto.getFixtureId()));
				log.info(" f: " + f);
				predict.setFixture(f);
				predict.setCreateDate(new Date());
				predictRepository.save(predict);
				
				log.info(" save: " + predict.getId());
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return true;
	}

	/*
	@Override
	public Boolean update(PredictDto predictDto) {
		log.info(" update");
		
		return true;
	}

	@Override
	public Boolean delete(Long id) {
		log.info(" delete");
		
		return true;
	}
	*/

	@Override
	public List<PredictDto> findByUserId(Integer userId) {
		log.debug(" findByUserId");
		
		List<PredictDto> result = new ArrayList<PredictDto>();
		
		try {
			List<Predict> listPredict = predictRepository.findByUserId(userId);
			for (Predict predict : listPredict) {
				PredictDto predictDto = new PredictDto();
				predictDto = predictMapper.toDtoBean(predict);
				predictDto.setFixtureId((predict.getFixture() + ""));
				
				result.add(predictDto);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<PredictDto> findByWeek(Short week) {
		log.debug(" findByWeek");
		
		List<PredictDto> result = new ArrayList<PredictDto>();
		
//		try {
//			List<Predict> listPredict = predictRepository.findByWeek(week);
//			for (Predict predict : listPredict) {
//				PredictDto predictDto = new PredictDto();
//				predictDto = predictMapper.toDtoBean(predict);
//				predictDto.setFixtureId((predict.getFixture() + ""));
//				
//				result.add(predictDto);
//			}
//		}
//		catch (Exception ex) {
//			log.error(ex, ex);
//		}
		
		return result;
	}
	
	@Override
	public List<PredictDto> findByUserIdAndWeek(Integer userId, Short week) {
		log.debug(" findByUserAndWeek");
		
		List<PredictDto> result = new ArrayList<PredictDto>();
		
		try {
			List<Predict> listPredict = predictCustomRepository.findByUserIdAndWeek(userId, week);
			for (Predict predict : listPredict) {
				PredictDto predictDto = new PredictDto();
				predictDto = predictMapper.toDtoBean(predict);
				predictDto.setFixtureId((predict.getFixture().getId() + ""));
				
				result.add(predictDto);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public List<PredictResultDto> weeklyResult(Short week) {
		log.debug(" weeklyResult: " + week);
		
		List<PredictResultDto> result = new ArrayList<PredictResultDto>();
		
		try {
			//
			List<Fixture> listFixture =  fixtureRepository.findByWeek(week);
			
			//
			Map<Integer, Map<Long, Predict>> mapUserPredict = new HashMap<Integer, Map<Long, Predict>>();
//			List<Predict> listPredict = predictRepository.findByWeekOrderByUserIdAsc(week);
			List<Predict> listPredict = new ArrayList<Predict>();
			for (Predict predict : listPredict) {
				if (mapUserPredict.containsKey(predict.getUserId()) == false) {
					mapUserPredict.put(predict.getUserId(), new HashMap<Long, Predict>());
				}
				
				Map<Long, Predict> mapPredict = mapUserPredict.get((predict.getUserId()));
				mapPredict.put(predict.getFixture().getId(), predict);
				
				mapUserPredict.put(predict.getUserId(), mapPredict);
			}
			
			//
			List<User> listUser = userRepository.findAll();
			for (User user : listUser) {
				PredictResultDto predictResultDto = PredictResultDto.builder().displayName(PredictHelper.getDisplayName(user)).point(0).build();
				
				List<PredictResultDetailDto> listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
				for (Fixture fixture : listFixture) {
					PredictResultDetailDto predictResultDetailDto = new PredictResultDetailDto();
					predictResultDetailDto.setHomeScore(Helper.nullObj2Blank(fixture.getHomeScore()) + "");
					predictResultDetailDto.setAwayScore(Helper.nullObj2Blank(fixture.getAwayScore()) + "");
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
								predictResultDto.setDisplayPoint("Y");
								predictResultDetailDto.setPoint(Helper.string2Integer(PredictHelper.calculatePoint(fixture, predict) + 1 + ""));
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
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<PredictResultDto> result(Integer userId) {
		log.debug(" result: " + userId);
		
		List<PredictResultDto> result = new ArrayList<PredictResultDto>();
		
		try {
			User user = userRepository.findOne(userId);
			//
			List<Fixture> listFixture =  fixtureRepository.findByLeague(Consts.EURO_2016);
			
			//
			Map<Integer, Map<Long, Predict>> mapUserPredict = new HashMap<Integer, Map<Long, Predict>>();
			List<Predict> listPredict = predictRepository.findByUserId(userId);
			for (Predict predict : listPredict) {
				if (mapUserPredict.containsKey(predict.getUserId()) == false) {
					mapUserPredict.put(predict.getUserId(), new HashMap<Long, Predict>());
				}
				
				Map<Long, Predict> mapPredict = mapUserPredict.get((predict.getUserId()));
				mapPredict.put(predict.getFixture().getId(), predict);
				
				mapUserPredict.put(predict.getUserId(), mapPredict);
			}
			
			PredictResultDto predictResultDto = PredictResultDto.builder().displayName(PredictHelper.getDisplayName(user)).point(0).build();
				
			List<PredictResultDetailDto> listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
			for (Fixture fixture : listFixture) {
				PredictResultDetailDto predictResultDetailDto = new PredictResultDetailDto();
				predictResultDetailDto.setRound(fixture.getRound() + "");
				predictResultDetailDto.setPredictHomeScore("");
				predictResultDetailDto.setPredictAwayScore("");
				predictResultDetailDto.setPoint(0);
					
				if (mapUserPredict.get(userId) != null) {
					Map<Long, Predict> mapPredict = mapUserPredict.get((userId));
					if (mapPredict.get(fixture.getId()) != null) {
						Predict predict = mapPredict.get(fixture.getId());
						predictResultDetailDto.setPredictHomeScore(Helper.null2Blank(predict.getHomeScore() + ""));
						predictResultDetailDto.setPredictAwayScore(Helper.null2Blank(predict.getAwayScore() + ""));
						if (fixture.getHomeScore() != null && fixture.getAwayScore() != null) {
							predictResultDetailDto.setPoint(Helper.string2Integer(PredictHelper.calculatePoint(fixture, predict) + "") + 1);
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
			
			log.debug(" result Success");
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<UserPointDto> standing(String round) {
		log.debug(" standing (round: " + round + ")");
		
		List<UserPointDto> result = new ArrayList<UserPointDto>();
		
		try {
			
			int championId = 0;
			
			//
			Map<Long, Fixture> mapFixture = new HashMap<Long, Fixture>();
			List<Fixture> listFixture = round == null ? fixtureRepository.findAll() : fixtureRepository.findByRound(round);
			for (Fixture fixture : listFixture) {
				mapFixture.put(fixture.getId(), fixture);
				
				if (Helper.null2Blank(fixture.getRound() + "").equals(Consts.ROUND_2)) {
					int homeScore = Helper.string2Integer(Helper.null2Zero(fixture.getHomeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomeExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomePenaltyScore() + ""));
					int awayScore = Helper.string2Integer(Helper.null2Zero(fixture.getAwayScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayPenaltyScore() + ""));
					log.debug(" homescore: " + homeScore);
					log.debug(" awayscore: " + awayScore);
					if (homeScore > awayScore) {
						championId = fixture.getHome().getId();
					}
					if (awayScore > homeScore) {
						championId = fixture.getAway().getId();
					}
					log.debug(" championId: " + championId);
				}
			}
			
			//
			Map<Integer, PredictChampion> mapPredictChampion = new HashMap<Integer, PredictChampion>();
			List<PredictChampion> listPredictChampion = predictChampionRepository.findAll();
			for (PredictChampion predictChampion : listPredictChampion) {
				mapPredictChampion.put(predictChampion.getUserId(), predictChampion);
			}
			
			Map<Integer, Integer> mapUserPoint = new HashMap<Integer, Integer>();

			List<Predict> listPredict = predictRepository.findAll(new Sort(Direction.ASC, "user"));
			for (Predict predict : listPredict) {
				if (mapUserPoint.containsKey(predict.getUserId()) == false) {
					mapUserPoint.put(predict.getUserId(), 0);
				}

				Fixture fixture = mapFixture.get(predict.getFixture());
				if (fixture != null && fixture.getHomeScore() != null && fixture.getAwayScore() != null) {
					mapUserPoint.put(predict.getUserId(), mapUserPoint.get(predict.getUserId()) + PredictHelper.calculatePoint(fixture, predict) + 1);
				}
			}
			
			List<User> listUser = userRepository.findAll();
			for (User user : listUser) {
				UserPointDto userPointDto = new UserPointDto();
				userPointDto.setUsername(user.getUsername());
				userPointDto.setPoint(0);
				userPointDto.setPredictCount(0);
				userPointDto.setCorrectResult(0);
				userPointDto.setCorrectResultAndScore(0);
				userPointDto.setIncorrectResult(0);
				userPointDto.setExtraPoint(-1);
				
				if (mapUserPoint.get(user.getUsername()) != null) {
					userPointDto.setPoint(mapUserPoint.get(user.getUsername()));
				}
				
				for (Predict predict : listPredict) {
					if (predict.getUserId() == user.getUserId()) {
						//
						Fixture fixture = mapFixture.get(predict.getFixture());
						if (fixture != null && fixture.getHomeScore() != null && fixture.getAwayScore() != null) {
							//
							userPointDto.setPredictCount(userPointDto.getPredictCount() + 1);
								
							//
							Short point = PredictHelper.calculatePoint(fixture, predict);
							if (point > 0) {
								if (point == 3) {
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
				}
				
				//
				PredictChampion p = mapPredictChampion.get(user.getUsername());
				if (p != null) {
					userPointDto.setExtraPoint(1);
					if (championId > 0) {
						if (p.getTeamId().intValue() == championId) {
							if (p.getRound().equals(Consts.ROUND_8)) {
								userPointDto.setExtraPoint(16);
							}
							if (p.getRound().equals(Consts.ROUND_4)) {
								userPointDto.setExtraPoint(8);
							}
							if (p.getRound().equals(Consts.ROUND_2)) {
								userPointDto.setExtraPoint(4);
							}
						}
						else {
							userPointDto.setExtraPoint(0);
						}
					}
					else {
						for (Fixture fixture : listFixture) {
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
								}
							}
						}
					}
				}
				
				userPointDto.setTotalPoint(userPointDto.getPoint() + ((userPointDto.getExtraPoint() == -1 || userPointDto.getExtraPoint() == 1) ? 0 : userPointDto.getExtraPoint()));
				
				result.add(userPointDto);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}
	
//	@Override
//	public Boolean updatePoint(Short week) {
//		log.debug(" updatePoint (week: " + week + ")");
//		
//		Boolean result = false;
//		
//		try {
//			//
//			Map<Long, Fixture> mapFixture = new HashMap<Long, Fixture>();
//			List<Fixture> listFixture =  fixtureRepository.findByWeek(week);
//			for (Fixture fixture : listFixture) {
//				mapFixture.put(fixture.getId(), fixture);
//			}
//			
//			//
//			List<Predict> listPredictUpdate = new ArrayList<Predict>();
//			
//			List<Predict> listPredict = predictRepository.findByWeek(week);
//			for (Predict predict : listPredict) {
//				Fixture fixture = mapFixture.get(predict.getFixture());
//				
//				predict.setPoint(PredictHelper.calculatePoint(fixture, predict));
//			}
//			
//			predictRepository.save(listPredictUpdate);
//		}
//		catch (Exception ex) {
//			log.error(ex, ex);
//		}
//		
//		return result;
//	}

	@Override
	public Boolean predictChampion(PredictChampionDto predictChampionDto) {
		log.debug(" save predict champion");
		
		Boolean result = false;
		
		try {
			PredictChampion predictChampionToDelete = predictChampionRepository.findByUserId(predictChampionDto.getUserId());
			if (predictChampionToDelete != null) {
				predictChampionRepository.delete(predictChampionToDelete);
			}
	
			if (predictChampionDto.getTeamId() != null) {
				PredictChampion predictChampion = predictChampionMapper.toPersistenceBean(predictChampionDto);
				predictChampion.setCreateDate(new Date());
				predictChampionRepository.save(predictChampion);
					
				log.info(" save success: " + predictChampion.getId());
			}

			result = true;
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public PredictChampionDto findPredictChampionByUserId(Integer userId) {
		log.debug(" findPredictChampionByUserId");
		
		PredictChampionDto predictChampionDto = null;
		
		try {
			PredictChampion predictChampion = predictChampionRepository.findByUserId(userId);
			if (predictChampion != null) {
				predictChampionDto = predictChampionMapper.toDtoBean(predictChampion);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return predictChampionDto;
	}
	
	@Override
	public List<PredictChampionDisplayDto> resultPredictChampion() {
		log.debug(" result predict champion");
		
		List<PredictChampionDisplayDto> result = new ArrayList<PredictChampionDisplayDto>();
		
		try {
			//
			List<PredictChampion> listPredictChampion = predictChampionRepository.findAll();
			Map<Integer, PredictChampion> mapPredictChampion = new HashMap<Integer, PredictChampion>();
			for (PredictChampion prdictChampion : listPredictChampion) {
				mapPredictChampion.put(prdictChampion.getUserId(), prdictChampion);
			}
			
			//
			List<Team> listTeam = teamRepository.findByLeague(Consts.EURO_2016);
			Map<Integer, Team> mapTeam = new HashMap<Integer, Team>();
			for (Team team : listTeam) {
				mapTeam.put(team.getId(), team);
			}
			
			//
			int championId = 0;
			List<Fixture> listFixture =  fixtureRepository.findAll();
			for (Fixture fixture : listFixture) {
				if (Helper.null2Blank(fixture.getRound() + "").equals("2")) {
					int homeScore = Helper.string2Integer(Helper.null2Zero(fixture.getHomeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomeExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getHomePenaltyScore() + ""));
					int awayScore = Helper.string2Integer(Helper.null2Zero(fixture.getAwayScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayExtraTimeScore() + "")) + Helper.string2Integer(Helper.null2Zero(fixture.getAwayPenaltyScore() + ""));
					log.debug(" homescore: " + homeScore);
					log.debug(" awayscore: " + awayScore);
					if (homeScore > awayScore) {
						championId = fixture.getHome().getId();
					}
					if (awayScore > homeScore) {
						championId = fixture.getAway().getId();
					}
					log.debug(" championId: " + championId);
				}
			}
			
			//
			List<User> listUser = userRepository.findAll();
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
							if (predictChampion.getRound().equals(Consts.ROUND_8)) {
								predictChampionDisplayDto.setPoint("16");
							}
							else if (predictChampion.getRound().equals(Consts.ROUND_4)) {
								predictChampionDisplayDto.setPoint("8");
							}
							else if (predictChampion.getRound().equals(Consts.ROUND_2)) {
								predictChampionDisplayDto.setPoint("4");
							}
						}
						else {
							predictChampionDisplayDto.setPoint("0");
						}
					}
					else {
						predictChampionDisplayDto.setPoint("?");
						for (Fixture fixture : listFixture) {
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
					}
				}
				
				result.add(predictChampionDisplayDto);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public List<UserPredictPerformanceDto> performance(Integer userId) {
		log.debug(" performance: " + userId);
		
		List<UserPredictPerformanceDto> listUserPredictPerformanceDto = new ArrayList<UserPredictPerformanceDto>();
		
		try {
			//
			List<Predict> listPredict = predictRepository.findByUserId(userId);
			Map<Long, Predict> mapPredict = new HashMap<Long, Predict>();
			for (Predict predict : listPredict) {
				mapPredict.put(predict.getFixture().getId(), predict);
			}
			
			//
			UserPredictPerformanceDto userPredictPerformanceDto = null;
			List<PredictResultDetailDto> listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
			
			int rec = 0;
			Short w = 0;
			Date day = null;
			List<Fixture> listFixture =  fixtureRepository.findAll();
			for (Fixture fixture : listFixture) {
				rec++;
				
				//
				if (rec > 1 && fixture.getWeek().shortValue() != w.shortValue()) {
					userPredictPerformanceDto = new UserPredictPerformanceDto();
					userPredictPerformanceDto.setWeek(w + "");
					userPredictPerformanceDto.setDay(Helper.date2String(day));
					userPredictPerformanceDto.setListFixture(listPredictResultDetailDto);
					listUserPredictPerformanceDto.add(userPredictPerformanceDto);
					listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
				}
				
				//
				PredictResultDetailDto predictResultDetailDto = new PredictResultDetailDto();
				predictResultDetailDto.setHomeTitle(fixture.getHome().getTitle());
				predictResultDetailDto.setAwayTitle(fixture.getAway().getTitle());
				predictResultDetailDto.setHomeShortTitle(fixture.getHome().getShortTitle());
				predictResultDetailDto.setAwayShortTitle(fixture.getAway().getShortTitle());
				predictResultDetailDto.setHomeScore(Helper.nullObj2Blank(fixture.getHomeScore()) + "");
				predictResultDetailDto.setAwayScore(Helper.nullObj2Blank(fixture.getAwayScore()) + "");
				predictResultDetailDto.setHomeExtraTimeScore(Helper.nullObj2Blank(fixture.getHomeExtraTimeScore()) + "");
				predictResultDetailDto.setAwayExtraTimeScore(Helper.nullObj2Blank(fixture.getAwayExtraTimeScore()) + "");
				predictResultDetailDto.setHomePenaltyScore(Helper.nullObj2Blank(fixture.getHomePenaltyScore()) + "");
				predictResultDetailDto.setAwayPenaltyScore(Helper.nullObj2Blank(fixture.getAwayPenaltyScore()) + "");
				
				//
				Predict predict = mapPredict.get(fixture.getId());
				if (predict != null) {
					predictResultDetailDto.setPredictHomeScore(Helper.nullObj2Blank(predict.getHomeScore()) + "");
					predictResultDetailDto.setPredictAwayScore(Helper.nullObj2Blank(predict.getAwayScore()) + "");
					if (fixture.getHomeScore() != null && predictResultDetailDto.getHomeScore() != null) {
						predictResultDetailDto.setPoint(Helper.string2Integer(PredictHelper.calculatePoint(fixture, predict) + 1 + ""));
					}
				}
				
				//
				listPredictResultDetailDto.add(predictResultDetailDto);
				
				//
				if (rec == listFixture.size()) {
					userPredictPerformanceDto = new UserPredictPerformanceDto();
					userPredictPerformanceDto.setWeek(fixture.getWeek() + "");
					userPredictPerformanceDto.setDay(Helper.date2String(fixture.getFixtureDate()));
					userPredictPerformanceDto.setListFixture(listPredictResultDetailDto);
					listUserPredictPerformanceDto.add(userPredictPerformanceDto);
					listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
				}
				
				w = fixture.getWeek().shortValue();
				day = fixture.getFixtureDate();
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return listUserPredictPerformanceDto;		
	}

}