# Admissions REST API

A production-style REST API for managing applicant admissions, including multi-step workflows, validation, reviewer comments, and search functionality. The project uses Spring Boot, PostgreSQL, and Flyway migrations, and provides full API documentation and a presentation overview.

# Live API
## Base URL: 
 https://admissions-api-telt.onrender.com/api/applications 

Use this endpoint to interact with the live deployment (CRUD, status transitions, reviewer notes, pagination, sorting, etc.).

## Project Presentation

A brief slide deck summarizing architecture, features, and design decisions
https://docs.google.com/presentation/d/1Q8dMh2VksbGYExQYfLnfW1l9HrIeXJp4/edit?usp=sharing&ouid=112357463381889706000&rtpof=true&sd=true 

## Full API Documentation (PDF)

A complete project documentation PDF covering features, workflow, validation, database design, migrations, and testing:
https://drive.google.com/file/d/1rLCcnBNuXFeWpZ91vGh0cImeF9DChabm/view?usp=sharing 

## Features

Applicant CRUD with soft-delete

Status workflow: Pending → In Review → Accepted/Rejected

Reviewer notes and comments

Search by email or program

Pagination & sorting

Validation groups + optimistic locking

PostgreSQL persistence with Flyway migrations

Comprehensive Swagger/OpenAPI documentation

## Tech Stack

Java 17+

Spring Boot

PostgreSQL

Flyway

Swagger / OpenAPI
