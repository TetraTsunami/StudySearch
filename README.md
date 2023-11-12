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

# Screenshots
![Screenshot 2023-11-12 at 10-17-20 StdyBddy (Small)](https://github.com/TetraTsunami/StudySearch/assets/78718829/a7f2b1b6-96e7-4985-9d67-d25711fa2b4d)
![Screenshot 2023-11-12 at 10-16-41 StdyBddy (Small)](https://github.com/TetraTsunami/StudySearch/assets/78718829/19e10457-1258-4a0a-93dd-5ce1b41b9dc5)
![Screenshot 2023-11-12 at 10-15-42 StdyBddy (Small)](https://github.com/TetraTsunami/StudySearch/assets/78718829/6f00093e-c03d-4b79-b22c-87907590f6a2)
![Screenshot 2023-11-12 at 10-13-48 StdyBddy (Small)](https://github.com/TetraTsunami/StudySearch/assets/78718829/23667557-0d36-4019-8c32-1691ea0b6fe4)
![Screenshot 2023-11-12 at 10-03-02 Log in StudySearch (Small)](https://github.com/TetraTsunami/StudySearch/assets/78718829/bb6d245e-2bd7-4623-ad78-56bb2db178af)
![Screenshot 2023-11-12 at 10-02-45 StdyBddy (Small)](https://github.com/TetraTsunami/StudySearch/assets/78718829/b7eee44c-df8c-4332-b048-d72053a8c30e)


## Setup guide
1. Clone the project
2. TODO: Reverse proxy guide? Caddy reference file?
3. Add `.env` file in project root with (TODO - PROPERTIES HERE)
4. Run `./gradlew bootBuildImage`
5. Run `docker-compose up -d`
