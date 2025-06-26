# 🚀 Project 1: FocusFlow – Time Blocking & Productivity Tracker

---

## 🧠 Overview

**FocusFlow** is a web application designed to help users structure their day into focused time blocks.  
Each block represents a period of deep work or focused activity, similar to Pomodoro-style sessions.

---

### ⏱️ Each time block includes:
- **Title**
- **Objective**
- **Duration** (custom or Pomodoro-style)
- **Status**: Scheduled, Running, Completed, Skipped
- **Optional notes**

---

### 💡 Ideal for:
- Study sessions
- Programming or deep technical work
- Task preparation
- Exams or interview prep

---

## 🧰 Tech Stack

| Component        | Technology                                                    |
|------------------|---------------------------------------------------------------|
| 🧠 **Backend**     | Java 21 + Spring Boot 3                                    |
| 🧱 **Architecture** | Hexagonal Architecture + Domain-Driven Design             |
| 🔁 **Event Broker** | Apache Kafka (BlockStarted, BlockCompleted)               |
| ⚡ **Cache**        | Redis (active block tracking, duplication prevention)     |
| 💾 **Database**     | PostgreSQL (user history and session data)                |
| 🌍 **Frontend**     | React + TailwindCSS                                       |
| 📲 **API**          | RESTful API (secured with JWT)                            |
| 🔐 **Authentication** | JWT + Spring Security                                   |
| 🚀 **CI/CD**        | GitHub Actions (test + build + Docker image)              |
| 🐳 **Containers**   | Docker + Docker Compose                                   |
| 🧪 **Testing**      | JUnit 5 + Mockito + Testcontainers                        |

---

## 🧩 Core Modules / Features

| Feature                 | Details                                                                            |
|-------------------------|------------------------------------------------------------------------------------|
| ✅ **Create Time Block** | Title, duration, objective, optional notes                                       |
| ✅ **Start Block**       | Triggers `BlockStartedEvent` via Kafka                                           |
| ✅ **Complete or Skip**  | Updates status and emits relevant events                                         |
| ✅ **Block History**     | List of past blocks with filters                                                 |
| ✅ **React Dashboard**   | Real-time view of current, scheduled, and completed blocks                       |
| ✅ **Redis Caching**     | Ensures only one active block per user                                           |
| ✅ **CI/CD**             | Automated testing, building, and Docker image via GitHub Actions                 |
| ✅ **Clean Architecture**| Hexagonal structure with Domain, Application, and Adapter separation             |

---

# 🧩 FocusFlow – UI Screens Overview

---

## 🖥️ 1. Dashboard (Home) – *"Your day at a glance"*

**Purpose:** Show current time block, upcoming ones, and quick stats.

**Elements:**
- 🔴 Active time block (with countdown)
- ⏭️ Next scheduled blocks
- ✅ Summary of completed/remaining blocks
- ⏱️ Start / Skip / Complete buttons
- 🔄 Real-time updates (via WebSocket or polling)

> 🧠 Think **Trello + Pomodoro Timer**, but vertical and structured.

---

## 📅 2. Schedule View / Planner

**Purpose:** Let users create and organize time blocks like a calendar.

**Elements:**
- 🗓 Drag-and-drop timeline or list of blocks
- **"Add Block" Modal**:
  - Title
  - Objective
  - Duration selector (e.g., 25 min or custom)
  - Optional notes
- ✏️ Edit/Delete controls

> 📌 Similar to **Google Calendar’s Day View**, but focused on deep work.

---

## 🧠 3. Create/Edit Time Block Modal

**Fields:**
- Block title
- Objective / Description
- Duration (custom or preset Pomodoro)
- Notes (optional)
- Start time (if scheduling ahead)
- Recurrence? *(optional future enhancement)*

---

## 📊 4. History / Analytics

**Purpose:** Help users reflect on their productivity patterns.

**Elements:**
- Filterable list of past blocks (by date/status/tag)
- 📊 Pie or bar charts: Time spent by category or day
- 📜 Block log: Titles + durations + notes
- ⬇️ Export options: CSV / JSON *(nice to have)*

---

## 🔐 5. Login / Register

**Authentication:** Using JWT + Spring Security.

**Elements:**
- Standard email/password form
- "Remember me" token
- *(Optional future)* Social login support

---

## ⚙️ 6. Settings / Profile Page

**Purpose:** Let users personalize their experience.

**Elements:**
- Default block duration
- Time zone
- Notification preferences (browser/email)
- Theme switch: dark / light

---

## 💡 Optional / Bonus Screens

### 🧘 Focus Mode / Fullscreen Timer
- Big countdown
- Minimal distractions
- Block title + objective

### 🧪 Admin Panel (for multi-user version)
- User management
- System stats
- Debug logs / audit trails

---

## 🧱 Frontend Stack Summary

| Purpose             | Tool                     |
|---------------------|--------------------------|
| UI Framework        | **React + TailwindCSS**  |
| Navigation          | React Router             |
| HTTP Client         | Axios or native Fetch    |
| Real-Time Sync      | WebSockets or SSE        |
| State Management    | Redux or Zustand *(optional)* |

---
