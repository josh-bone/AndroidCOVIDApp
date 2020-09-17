# AndroidCOVIDApp


<img src="images/eng_logo.jpg" width="170">

Created by Joshua Bone and Salem Bugshan

EC463 - Sr. Design Miniproject 

Boston University

Below are screenshots of what the app would look like.

This is a screenshot of the login screen. This activity is launched on startup.

<img src="images/welcomeScreen.png" width="150">

This is a screenshot of the survey screen.

<img src="images/survey_screen.png" width="150">

Here we display the students who have not completed the survey:

<img src="images/nonSurveyed.png" width="150">

<img scr="images/symptomaticScreen.png" width="150">

Here is the (API) info dashboard, after we hit the rate limit during testing:

<img src="images/dashboard.png" width="150">

Unfortunately, I didn't grab a screenshot before the rate-limit was hit, when it was displaying real data.


External Dependencies:

We use Espresso for instrumentation tests.

We use Retrofit 2 for api requests from covid19api.com

Firebase is used for google sign-on, and we also take advantage of their Real Time Database to store the results of students' symptom-survey.