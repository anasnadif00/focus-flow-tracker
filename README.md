🚀 Project 1: FocusFlow – Time Blocking & Productivity Tracker
🧠 Overview
FocusFlow is a web application designed to help users structure their day into focused time blocks.
Each block represents a period of deep work or focused activity, similar to Pomodoro-style sessions.

Each time block includes:

Title

Objective

Duration (custom or Pomodoro-style)

Status (Scheduled, Running, Completed, Skipped)

Optional notes

Ideal for:

Study sessions

Programming or deep technical work

Task preparation

Exams or interview prep

🧰 Tech Stack
Component	Technology
🧠 Backend	Java 21 + Spring Boot 3
🧱 Architecture	Hexagonal Architecture + Domain-Driven Design
🔁 Event Broker	Apache Kafka (BlockStarted, BlockCompleted)
⚡ Cache	Redis (active block tracking, duplication prevention)
💾 Database	PostgreSQL (user history and session data)
🌍 Frontend	React + TailwindCSS
📲 API	RESTful API (secured with JWT)
🔐 Authentication	JWT + Spring Security
🚀 CI/CD	GitHub Actions (test + build + Docker image)
🐳 Containers	Docker + Docker Compose
🧪 Testing	JUnit 5 + Mockito + Testcontainers

🧩 Core Modules / Features
Feature	Details
✅ Create Time Block	Title, duration, objective, optional notes
✅ Start Block	Triggers BlockStartedEvent via Kafka
✅ Complete or Skip Block	Updates status and emits relevant events
✅ Block History	List of past blocks with filters
✅ React Dashboard	Real-time view of current, scheduled, and completed blocks
✅ Redis Caching	Ensures only one active block per user
✅ CI/CD	Automated testing, building, and Docker image via GitHub Actions
✅ Clean Architecture	Hexagonal structure with Domain, Application, and Adapter separation
