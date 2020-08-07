*** Settings ***
Documentation     A resource file with reusable keywords and variables.
...               Marko Golovko, Katsiaryna Yalovik
...               Środa 18-20
...               The system specific keywords created here form our own
...               domain specific language. They utilize keywords provided
...               by the imported SeleniumLibrary.
Library           SeleniumLibrary
Library           MyLibrary

*** Variables ***
${INDEX}            file:///D:/uniwer/VI%20semestr/TG/TestProject/page/index.html
${BROWSER}          chrome
${HOME BUTTON ID}   new_window_button
${NAVBAR LINK LOCATOR}  xpath=//div[@class="topnav"]//a
${CONTACT LINK}     Contact
${GALLERY LINK}     Gallery
${HOME LINK}        Home
${NEWS LINK}        News
${GALLERY LOCATOR}  xpath=//div[@class="row"]
${IMAGE LOCATOR}    xpath=//div[@class="row"]//img
${CONTACT FORM ID}  contact_form
${CONTACT NAME ID}  name
${CONTACT SURNAME ID}  surname
${CONTACT EMAIL ID}    email
${CONTACT MESSAGE ID}  message
${CONTACT COUNTRY ID}   country
${EMPTY}
${NEWS}          file:///D:/uniwer/VI%20semestr/TG/TestProject/page/news.html
${NEWS TITLE}        News


*** Keywords ***
Open Browser To Home Page
    Open Browser    ${INDEX}   ${BROWSER}

Browser is opened to index page
    Open Browser To Indeks Page

User press „Open home page in new window”
    Click Element    ${HOME BUTTON ID}

New home page should be open in new window
    Select Window    NEW
    ${tempB}=    Get Title
    Should Be Equal As Strings  ${tempB}  Home

User go to Contact page
    Click Link    ${CONTACT LINK}
    Wait Until Page Contains Element    ${CONTACT FORM ID}

New form should be empty
    Element Attribute Value Should Be     ${CONTACT NAME ID}  value    ${EMPTY}
    Element Attribute Value Should Be     ${CONTACT SURNAME ID}  value    ${EMPTY}
    Element Attribute Value Should Be     ${CONTACT EMAIL ID}  value    ${EMPTY}
    Element Text Should Be     ${CONTACT MESSAGE ID}    ${EMPTY}

User fill contact form
    Input Text    ${CONTACT NAME ID}   Marko
    Input Text    ${CONTACT SURNAME ID}   Golovko
    Input Text    ${CONTACT EMAIL ID}   markgolovko@gmail.com
    Select From List By Label    ${CONTACT COUNTRY ID}  France
    Input Text    ${CONTACT MESSAGE ID}   Hello
    Capture Page Screenshot

Submit contact form
    Submit Form   ${CONTACT FORM ID}

Appear alert and user approve it
    Alert Should Be Present

User go to Gallery page
    Click Link    ${Gallery LINK}
    Wait until page contains element    ${GALLERY LOCATOR}

User see 11 images
    ${count} =  Get Element Count   ${IMAGE LOCATOR}
    Should Be True  ${count} == 11

    @{imgs} =   Get WebElements  ${IMAGE LOCATOR}
    FOR   ${img}  IN  @{imgs}
           Log To Console   ${img.get_attribute('src')}
    END

User click ${link} and go to ${page}
    Click Link  ${link}
    ${page title} =    Get Title
    Should Be Equal   ${page title}   ${page}

Contact form area
    Click Link    ${CONTACT LINK}
    ${size} =  Get Element Size    ${CONTACT FORM ID}
    ${area} =  Size area  ${size}
    Should Be True  ${area} > 300000

All links with id should be defirent
    ${links} =   Get All Links
    ${result} =     Links has different id  ${links}
    Should Be True  ${result}
