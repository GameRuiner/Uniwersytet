*** Settings ***
Documentation     A test suite with page tests.
...               Marko Golovko, Katsiaryna Yalovik
...               Środa 18-20
Test Setup        Open Browser To Home Page
Test Teardown     Close Browser
Resource          resource.robot

*** Test Cases ***

Open new home page
    When user press „Open home page in new window”
    Then new home page should be open in new window

Contact form
    When user go to Contact page
    Then new form should be empty
    When user fill contact form
    When submit contact form
    Then appear alert and user approve it

Gallery
    When user go to Gallery page
    Then user see 11 images

Navigation bar
    [Template]   user click ${link} and go to ${page}
    ${CONTACT LINK}     ${CONTACT LINK}
    ${GALLERY LINK}     ${GALLERY LINK}
    ${HOME LINK}        ${HOME LINK}
    ${NEWS LINK}        ${NEWS LINK}

Contact form area
     contact form area

Home page links
    all links with id should be defirent
