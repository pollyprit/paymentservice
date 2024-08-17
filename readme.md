[E-Commerce System Design]


![E-Commerce System Design](ecom.jpg)



Payment service deals with the payments for our e-commerce application. It integrates with Third-Party payments service providers like Razor Pay or Stripe. This also simplifies our application architecture where we don’t have to deal with sensitive information on behalf of our users.

Primary functionalities provided by the Payment Service:
a.	Make a payment
b.	Call a webhook

REST endpoints:
 POST /payment
 POST /webhook
