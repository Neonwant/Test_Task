1. SELECT Name FROM employees AS t WHERE t.Salary > (SELECT Salary FROM Employees WHERE EmployeeID = t.BossId);

2. SELECT Name, MAX(Salary), DeparmentID FROM Employees GROUP By DeparmentID;

3. SELECT Name FROM Departments AS t WHERE (SELECT COUNT(*) FROM Employees WHERE DeparmentID = t.DepartmentId) < 3;

4. SELECT * FROM Employees AS t WHERE ( Select count(*) FROM Employees WHERE DeparmentID = t.DeparmentID AND BossId IS NOT NULL) = 0;

5. SELECT DeparmentID, SUM(Salary) FROM Employees GROUP BY DeparmentID;