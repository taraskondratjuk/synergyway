select *
from flight
where aircompany_id = 1;

select *
from airplane
where fuel_capacity < 1000;

select name, number_of_flights as NumberFlight
from airplane
where number_of_flights < 3;

select name, number_of_flights as NumberFlight
from airplane
where number_of_flights > 3;

select name, number_of_flights as NumberFlight
from airplane
where number_of_flights in (5, 10, 14);

select *
from airplane
where flight_distance > 6000;

select max(flight.estimated_flight_time) as MaxDistance
from flight;

select min(airplane.number_of_flights) as MinNumberOfFlights, airplane.name
from airplane;

select max(airplane.number_of_flights) as MinNumberOfFlights, airplane.name
from airplane;

select a.name, max(flight_distance) as MaxFlithtDistance
from aircompany
         join airplane a on aircompany.id = a.aircompany_id
         join flight f on aircompany.id = f.aircompany_id
group by name
order by MaxFlithtDistance desc;


select aircompany.name, max(number_of_flights) as MaxCountOfFlights
from aircompany
         join flight f on aircompany.id = f.aircompany_id
         join airplane a on aircompany.id = a.aircompany_id
group by name
order by MaxCountOfFlights desc;


select aircompany.name, max(distance) as MaxDistance
from aircompany
join flight f on aircompany.id = f.aircompany_id
group by name
order by MaxDistance desc
limit 1;