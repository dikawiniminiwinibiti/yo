package com.example.nba.model.detail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailTeamResponse{

	@SerializedName("teams")
	private List<DetailTeamItem> teams;

	public void setTeams(List<DetailTeamItem> teams){
		this.teams = teams;
	}

	public List<DetailTeamItem> getDetailTeams(){
		return teams;
	}

	@Override
 	public String toString(){
		return 
			"DetailTeamResponse{" + 
			"teams = '" + teams + '\'' + 
			"}";
		}
}