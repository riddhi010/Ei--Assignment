# Virtual Classroom Manager

The **Virtual Classroom Manager** is a console-based Java application that simulates a simple online classroom system.  
It allows administrators (users) to create classrooms, add students, schedule assignments, and track submissions in real time.

---

## Overview
This project demonstrates **Object-Oriented Design** combined with key **Design Patterns** to build a modular and scalable classroom management system.

Key features:
- Create multiple classrooms.
- Enroll students into classrooms.
- Schedule assignments and automatically notify students.
- Submit assignments and track submissions.
- Real-time console notifications and logging.

---

## Folder structure: 

```
exercise2/
├── manager/
│   └── VirtualClassroomManager.java    # Core logic for managing classrooms
├── model/
│   ├── Assignment.java                 # Represents an assignment entity
│   ├── AssignmentObserver.java         # Observer interface 
│   ├── Classroom.java                  # Classroom entity, manages students & assignments
│   └── Student.java                    # Represents a student (implements AssignmentObserver)
├── Main.java                           # Entry point with interactive menu
└── README.md                            # Project documentation
```


## Design Patterns Used
The project integrates **multiple design patterns** for maintainability and scalability:

1. **Observer Pattern (Behavioral)**  
   - **Where Used:** When a teacher schedules a new assignment, all students in the classroom are automatically notified.
   - **Benefit:** Decouples the assignment scheduling logic from the student notification mechanism.

2. **Singleton Pattern**  
   - The `Logger` acts as a shared global instance, ensuring a single point for log management.

---

## OOP Concepts Applied
- **Encapsulation**:  
  - Each class (e.g., `Classroom`, `Student`, `Assignment`) manages its own data and exposes only necessary methods.
- **Abstraction**:  
  - `AssignmentObserver` interface defines a contract for any observer of assignment updates.
- **Polymorphism**:  
  - Different observers can implement `update()` to handle notifications differently.
- **Composition**:  
  - `VirtualClassroomManager` contains and manages multiple `Classroom` objects.

---

## Commands & Features
The system is entirely menu-driven. After launching the program, you can:

| Command | Description |
|--------|-------------|
| **add_classroom** | Create a new virtual classroom. |
| **add_student** | Enroll a student in a specific classroom. |
| **schedule_assignment** | Create a new assignment and notify all students in that classroom. |
| **submit_assignment** | Allow a student to submit a scheduled assignment. |
| **list_classrooms** | Display all available classrooms. |
| **list_students** | View all students in a classroom. |
| **list_assignments** | View all assignments scheduled in a classroom. |

---

## Logging
The project uses Java’s built-in **`java.util.logging`** to provide:
- **Warnings** for invalid operations (e.g., adding a duplicate classroom).
- **Informational logs** for successful actions.


---

## 🖼️ Sample Output
> _(Replace this placeholder with a screenshot of your console output)_

