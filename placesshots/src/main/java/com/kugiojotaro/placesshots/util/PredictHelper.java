package com.kugiojotaro.placesshots.util;

import com.kugiojotaro.placesshots.entity.Fixture;
import com.kugiojotaro.placesshots.entity.Predict;
import com.kugiojotaro.placesshots.entity.User;

//import org.apache.log4j.Logger;

public class PredictHelper {

	//private static final Logger LOGGER = Logger.getLogger(PredictHelper.class);

	/*
	public static Short calculatePoint(Short homeScore, Short awayScore, Short predictHomeScore, Short predictAwayScore) {
		Short point = 0;
		
		try {
			if ((homeScore > awayScore && predictHomeScore > predictAwayScore) || (homeScore < awayScore && predictHomeScore < predictAwayScore) || (homeScore == awayScore && predictHomeScore == predictAwayScore)) {
				point = 1;
					
				if (homeScore == predictHomeScore && awayScore == predictAwayScore) {
					point = 3;
				}
			}
		}
		catch (Exception ex) {
			//LOGGER.error(ex, ex);
		}
		
		return point;
	}
	*/
	
	public static Short calculatePoint(Fixture fixture, Predict predict) {
		Short point = 0;
		
		try {
			if ((fixture.getHomeScore() > fixture.getAwayScore() && predict.getHomeScore() > predict.getAwayScore()) || (fixture.getHomeScore() < fixture.getAwayScore() && predict.getHomeScore() < predict.getAwayScore()) || (fixture.getHomeScore() == fixture.getAwayScore() && predict.getHomeScore() == predict.getAwayScore())) {
				point = 1;
					
				if (fixture.getHomeScore() == predict.getHomeScore() && fixture.getAwayScore() == predict.getAwayScore()) {
					point = 3;
				}
			}
		}
		catch (Exception ex) {
			//LOGGER.error(ex, ex);
		}
		
		return point;
	}
	
	public static String getDisplayName(User user) {
		if (user.getUserConnection() == null) {
			return user.getUsername();
		}
		else {
			return user.getUserConnection().getDisplayName();
		}
	}

}