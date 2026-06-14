# Modern Residential Electricity Bill Calculator

A console-based Java app that calculates monthly residential electricity bills for Sri Lankan domestic consumers. Supports **Standard Domestic** (tiered slabs) and **Time-of-Use (TOU)** tariffs, with **solar export credits**.

**Course:** BECS 12243 | **Group:** 22

---

## Features

- Standard domestic billing (0–30, 31–60, and 60+ kWh slabs)
- TOU billing (Day, Peak, Off-Peak)
- Solar net-metering credit (Rs. 25.00/kWh)
- Formatted monthly bill summary

## Billing Formula

```
Net Payable = (Energy Charge + Fixed Charge) − Solar Credit
```

Rates are based on the PUCSL approved tariff (effective 15 Oct 2025). See `Annex-2-Approved-Tariff-Table.jpg`.

---

## Project Structure

```
OOP Mini Project/src/
├── ElecBillCalculator.java   
├── TariffRate.java        
├── Consumer.java                
├── StandardConsumer.java     
├── TOUConsumer.java          
├── BillingService.java      
└── ResidentialBillingEngine.java 
```

---

## How to Run

**Requirements:** JDK 21+

```bash
cd "OOP Mini Project"
javac -d out src/*.java
java -cp out ElecBillCalculator
```

Or open `OOP Mini Project` in IntelliJ IDEA and run `ElecBillCalculator.java`.

---

## Team

| Student No. | Name | Files |
|-------------|------|-------|
| EC/2023/071 | K. L. S. D. Fernando | `ElecBillCalculator.java` |
| EC/2023/044 | W. P. J. Prabhashwara | `Consumer.java`, `TOUConsumer.java` |
| EC/2023/078 | M. A. M. Deepal | `TariffRate.java` |
| EC/2023/014 | D. D. S. V. De Alwis | `StandardConsumer.java` |
| EC/2023/037 | E. K. N. S. Ekanayake | `BillingService.java`, `ResidentialBillingEngine.java` |

---

**Repo:** [github.com/klsdfernando/OOP-Project-2ndSem](https://github.com/klsdfernando/OOP-Project-2ndSem)
