# Adapter Pattern

## Overview
**Type:** Structural Pattern  

The Adapter Pattern allows **incompatible interfaces** to work together by **converting the interface of a class into another interface expected by the client**.  

---

## **Use Case Demonstrated:** CSV Report Generator  

**Scenario:**  
A reporting system expects a standard `ReportGenerator` interface.  
The existing `CsvReport` class does not match this interface.  
Using an Adapter (`CsvReportAdapter`) allows the system to generate CSV reports **without modifying the existing CSV code**.

---

## UML Diagram
> <img width="987" height="581" alt="image" src="https://github.com/user-attachments/assets/10e3505d-5955-4eeb-93a7-f342dcd41707" />


---

## Output
> <img width="483" height="307" alt="image" src="https://github.com/user-attachments/assets/e3a5ec23-7a46-4d43-9e9c-745c407690ef" />


---

## Logging 
> <img width="708" height="98" alt="image" src="https://github.com/user-attachments/assets/85fde66d-3e3b-4d46-bb8f-ece6e6d1840a" />
