# StudySearch
MadHacks 2023 Project

# Inspiration

We were inspired by the inherent value of collaborative studying, coupled with the challenges students often face in forming and maintaining study groups. Our goal was to simplify and enhance this experience through the creation of a user-friendly web application.

## What it does

The process begins with students signing up for an account using Auth0 authentication. They then provide details about their classes and weekly availability. The unique feature of our application lies in the automatic generation of study groups based on the shared availability of its members.

## How we built it

The frontend of our application was crafted using Svelte/SvelteKit, featuring authentication powered by Auth0. MongoDB serves as our robust database, while the backend is implemented in Kotlin using the Spring Boot framework. Lastly, we used Caddy as a reverse proxy to allow the backend API and the frontend to be hosted on the same port (and to give us HTTPS support). 

## Challenges we ran into

Navigating user interactions and authentication complexities presented notable challenges. However, leveraging Auth0 significantly aided us in overcoming these hurdles. Additionally, the implementation of a backtracking-based algorithm for schedule selection proved to be intricate. We are also actively exploring the integration of a PDF parser to extract class schedules from PDF documents. The development of the availability-selecting grid widget posed its own set of difficulties.

## Accomplishments that we're proud of

Our pride stems from the successful creation of the availability-selecting grid widget, the overall project completion within stringent time limits, and the successful implementation of the backtracking-based scheduling algorithm.

## What we learned

The project provided invaluable lessons in the utilization of various libraries and tools. One key takeaway was the realization that opting for enterprise-grade systems might not always be the optimal choice, especially within tight time constraints.

## What's next for stdy.tech

We have exciting plans for the future of stdy.tech. Our roadmap includes the addition of a shared Google Doc for study group collaboration, a built-in in-app chat platform, a voting mechanism for choosing nearby study locations, and a general overhaul of the user interface. We aim to automate the blocking of time slots corresponding to users' class schedules on the availability grid widget. Beyond UW-Madison, we believe it's feasible to extend the majority of these features to other college campuses.

## Setup guide
1. Clone the project
2. TODO: Reverse proxy guide? Caddy reference file?
3. Add `.env` file in project root with (TODO - PROPERTIES HERE)
4. Run `./gradlew bootBuildImage`
5. Run `docker-compose up -d`
