package com.prueba.backend.service;

import com.prueba.backend.entity.Project;
import com.prueba.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project findById(Integer id){
        for(Project p : projectRepository.findAll()){
            if(p.getId_project().equals(id)){
                return  p;
            }
        }
        return  null;
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    public Project updateProject(Integer id, Project projectNew) {
        Project existingProject = this.findById(id);
        if (existingProject != null) {
            existingProject.setName_project(projectNew.getName_project());
            if (projectNew.getCompany() != null) {
                existingProject.setCompany(projectNew.getCompany());
            }
            return projectRepository.save(existingProject);
        }

        return null;
    }


    public Project deleteProject(Integer id) {
        Project project = findById(id);
        projectRepository.delete(project);
        return project;

    }




}
