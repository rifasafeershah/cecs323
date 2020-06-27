--1.
--returns 4 rows.
SELECT customerName, YEAR(orderDate) AS "Year", MONTH(orderDate) AS "Month", COUNT(orderNumber) AS "Amount Ordered"
FROM Customers NATURAL JOIN  Orders
GROUP BY customerName, YEAR(orderDate), MONTH(orderDate)
HAVING COUNT(orderNumber) > 2
ORDER BY COUNT(orderNumber);

--2.
--returns 8 rows.
SELECT lastName, firstName
FROM Employees e LEFT OUTER JOIN  Customers c ON e.EMPLOYEENUMBER = c.SALESREPEMPLOYEENUMBER
WHERE c.SALESREPEMPLOYEENUMBER IS NULL
ORDER BY lastName, firstName;

--3.
--returns 28 rows.
SELECT productName, COUNT(orderNumber) AS "Total"
FROM Products p
NATURAL JOIN (Orders o NATURAL JOIN OrderDetails od)
WHERE MONTH(orderDate) = 03
GROUP BY  productName
HAVING  COUNT(orderNumber) > 2
ORDER BY COUNT(orderNumber) DESC, productName ;

--4.
--returns 5 rows.
SELECT productName, orderDate, quantityOrdered
FROM products
NATURAL JOIN (orders NATURAL JOIN orderdetails)
WHERE quantityOrdered > 76;

--5.
--returns 6 rows.
SELECT  productLine, textDescription, COUNT(productScale) AS "Number of Scales"
FROM ProductLines NATURAL JOIN Products
GROUP BY productLine, textDescription
HAVING COUNT(productScale) > 5;
