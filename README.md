# Mars Rover Kata


## Design choices

Here we are! My take on this kata! （★￣∀￣★）

I've chosen to express the rover's own positioning as the space required by a rover on the grid.
<br>This translates to a rover position being any pair of coordinates from the **x** and **y** axis on a cartesian grid.

I've chosen to express the rover orientation following the grid's own cardinal directions.

Simplified even more, we could say in our case a rover...
<ul>
<li>is a movable object requiring 1 full square on a grid,</li>
<li>shall face one and only one orientation at any given time,</li>
<li>can either move, rotate, or rest.</li>
</ul>

In the exact same spirit, an obstacle is the space taken by any impassable terrain for the rover on the very same grid.

The grid features wrapping around the edges.

Say we're on a 4 by 4 grid, and a command sends the rover from (1,4) to (1,5), then it should land on (1,1) if there is no obstacle there.

Think of it as the good old snake phone game!


## How to try it

You should be able to launch the tests once the repo is cloned on your machine with the following command :

`/.mvnw test`

Feel free to add any in order to try the app's behavior!


## Here, have a few graphic details

![mars_rover](https://github.com/fklown/rover/blob/main/mars-rover.png)


#### Thanks for your interest, take care
