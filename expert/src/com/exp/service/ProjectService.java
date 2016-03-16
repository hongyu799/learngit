package com.exp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp.dao.ProjectDao;
import com.exp.model.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectDao projectDao;

	public List<Project> findProject(Project project) {
		return projectDao.findProject(project);
	}

	public Project findProjectById(Project project) {
		List<Project> result = projectDao.findProjectById(project);
		if (null != result && result.size() > 0)
			return result.get(0);
		else
			return null;
	}
	
	public Project findNewProject(Project project){
		List<Project> result = projectDao.findNewProject(project);
		if (null != result && result.size() > 0)
			return result.get(0);
		else
			return null;
	}
	
	public int updateDocStatus(Project project){
		return projectDao.updateDocStatus(project);
	}
}
