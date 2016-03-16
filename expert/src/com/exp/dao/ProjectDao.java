package com.exp.dao;

import java.util.List;

import com.exp.model.Project;

public interface ProjectDao {
	
	public List<Project> findProject(Project project);
	
	public List<Project> findProjectById(Project project);
	
	public List<Project> findNewProject(Project project);
	
	public int updateDocStatus(Project project);
}
