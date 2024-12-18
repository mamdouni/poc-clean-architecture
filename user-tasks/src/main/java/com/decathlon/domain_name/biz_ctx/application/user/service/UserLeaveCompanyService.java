package com.decathlon.domain_name.biz_ctx.application.user.service;

import com.decathlon.domain_name.biz_ctx.application.user.bpmn.ProcessStep;
import com.decathlon.domain_name.biz_ctx.application.user.bpmn.Workflow;
import com.decathlon.domain_name.biz_ctx.application.user.usecase.input.port.UserLeaveCompanyUseCase;
import com.decathlon.domain_name.biz_ctx.domain.models.Task;
import com.decathlon.domain_name.biz_ctx.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
/**
 * Persona : you are an expert in Java code, PlantUML Activity diagram and reserve engineering
 * Context :
 * Methods are annotated with 2 different annotations :
 *  - @Workflow to define the startup of a workflow or subWorkflow
 *  - @ProcessStep to define a step in a workflow or subWorkflow
 *
 *  I want you to generate the PlantUML Activity diagram from the code below
 *  You will use the name of annotations in the diagram
 *
 */
public class UserLeaveCompanyService implements UserLeaveCompanyUseCase {

   // Add business service, Rules, and Ports needed to execute this workflow

   @Override
   @Transactional
   @Workflow(name = "User leave the Company",
             documentation = "This workflow is used to handle the user leaving the company")
   public void processLeavingCompany(String userId) {

      boolean userExists = findUser(userId);
      if (!userExists) {
         sendUserUnknownedError(userId);
      }

      boolean userConnectedhaveRights = checkConnectedUserRightsToDisableUser(userId);
      if (!userConnectedhaveRights) {
         sendUserConnectedNotAuthorized(userId);
      }

      List<Task> notCompletedTasks        = findNotCompletedUserTasks(userId);
      List<User> usersWithReaffectedTasks = new ArrayList<>();
      if (!notCompletedTasks.isEmpty()) {
         Integer managerId = findTheManagerOfUser(userId);
         if (managerId != null) {
            usersWithReaffectedTasks = reassignTasksToManager(userId, managerId, notCompletedTasks);

         }
         else {
            usersWithReaffectedTasks = reassignTasksOnTheTeam(userId, notCompletedTasks);
         }
      }

      if (!usersWithReaffectedTasks.isEmpty()) {
         sendNotificationToUsersWithReaffectedTasks(usersWithReaffectedTasks);
      }

      disableTheUser(userId);

   }

   @ProcessStep(name = "Disable the user", documentation = "Disable the user")
   private void disableTheUser(final String userId) {

   }

   @ProcessStep(name = "Send notification to users with re-affected tasks",
                documentation = "Send a notification to the users with re-affected tasks")
   private void sendNotificationToUsersWithReaffectedTasks(final List<User> usersWithReaffectedTasks) {

   }

   @ProcessStep(name = "Reassign tasks on the team",
                documentation = "Reassign the tasks of the user to the team")
   private List<User> reassignTasksOnTheTeam(final String userId, final List<Task> notCompletedTasks) {
      List<User> users = new ArrayList<>();

      // List of users with re-affected tasks should be equals to 1..n users

      return users;
   }

   @ProcessStep(name = "Find the manager of the user",
                documentation = "Find the manager of the user")
   private Integer findTheManagerOfUser(final String userId) {
      Integer managerId = 1;

      return managerId;
   }


   @ProcessStep(name = "Reassign tasks to Manager", documentation = "Reassign the tasks of the user to the manager")
   private List<User> reassignTasksToManager(final String userId, final Integer managerId, final List<Task> notCompletedTasks) {
      List<User> users = new ArrayList<>();

      // List of users with re-affected tasks should be equals to only 1..1 user

      return users;
   }

   @ProcessStep(name = "Find not completed tasks of the user",
                documentation = "Find the not completed tasks of the user")
   private List<Task> findNotCompletedUserTasks(final String userId) {
      List<Task> tasks = new ArrayList<>();
      return tasks;
   }

   @ProcessStep(name = "Send user connected have wrong authorization",
                documentation = "Throws a blocking RuntimeException if the connected user does not have rights to disable the user")
   private void sendUserConnectedNotAuthorized(final String userId) {
      throw new RuntimeException(String.format("You are not authorized to disable the user id '%s'", userId));
   }


   @ProcessStep(name = "Send user unknowned error",
                documentation = "Throws a blocking RuntimeException if the user does not exist")
   private void sendUserUnknownedError(final String userId) {
      throw new RuntimeException(String.format("User with id '%s' does not exist", userId));
   }

   @ProcessStep(name = "Check if connected user has rights to disable user",
                documentation = "Throws a blocking RuntimeException if the connected user does not have rights to disable the user")
   private boolean checkConnectedUserRightsToDisableUser(final String userId) {
      boolean businessLayerCallResult = true;

      return businessLayerCallResult;
   }

   @ProcessStep(name = "Check if user exists",
                documentation = "Check if the user exists in the system")
   private boolean findUser(final String userId) {
      boolean businessLayerCallResult = true;

      return businessLayerCallResult;
   }


}




/**
 * https://www.planttext.com/
 *
 * @startuml
 * start
 * :User leave the Company;
 * note right: This workflow is used to handle the user leaving the company
 *
 * if (Check if user exists) then (yes)
 *   :Check if connected user has rights to disable user;
 *   if (Check if connected user has rights to disable user) then (yes)
 *     :Find not completed tasks of the user;
 *     if (Find not completed tasks of the user) then (not empty)
 *       :Find the manager of the user;
 *       if (Find the manager of the user) then (found)
 *         :Reassign tasks to Manager;
 *       else (not found)
 *         :Reassign tasks on the team;
 *       endif
 *       :Send notification to users with re-affected tasks;
 *     endif
 *     :Disable the user;
 *   else (no)
 *     :Send user connected have wrong authorization;
 *   endif
 * else (no)
 *   :Send user unknowned error;
 * endif
 * stop
 * @enduml
 */