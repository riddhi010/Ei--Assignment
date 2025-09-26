# Command Pattern

## Overview
**Type:** Behavioral Pattern  

**Overview:**  
The Command Pattern is a behavioral design pattern used to encapsulate requests as objects, allowing parameterization of clients with different requests, queuing of requests, logging, and supporting undoable operations.  
It decouples the object that invokes the operation from the one that knows how to perform it, making the system more flexible and extensible.

---

**Use Case Demonstrated:** Classroom Command Executor  

**Scenario:**  
A virtual classroom system needs to perform actions like starting a lecture, ending a lecture, or taking attendance.  
The system encapsulates each action as a command, which can be executed by a command invoker. This allows maintaining a log of executed actions and optionally undoing the last command.

---

## UML Diagram
> <img width="1174" height="511" alt="image" src="https://github.com/user-attachments/assets/d0a2e898-7b77-4d2a-bfbc-33630ed94aa0" />



---

## Output
> <img width="390" height="488" alt="image" src="https://github.com/user-attachments/assets/9ac347b7-5bfe-4066-aedb-34ce609799ef" />
> <img width="397" height="575" alt="image" src="https://github.com/user-attachments/assets/6dd56bd0-b862-44d7-abd4-c274ee7a4ae3" />

## Logging 
> <img width="697" height="225" alt="image" src="https://github.com/user-attachments/assets/48d8fe0d-77ef-4e95-bd11-40a8236e101e" />







---

