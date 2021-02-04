document.addEventListener('DOMContentLoaded', function(){
   
   let submitButton = document.getElementById("submit_button");
   submitButton.addEventListener('click', validateForm);
   
   let accountNumber = document.getElementsByName("numer_karty[]");
   let accountNumberRegex = new RegExp("^[0-9]{4}$");
   accountNumber.forEach(element => 
         element.addEventListener('blur', function() {validateInput(element, accountNumberRegex)})
   );

   let date = document.getElementsByName("data_waznosci")[0];
   let dateRegex = new RegExp("^[0-9]{2}/[0-9]{2}$");
   date.addEventListener('blur', function() {validateInput(date,dateRegex)});
   
   let name = document.getElementsByName("imie")[0];
   let nameRegex= new RegExp("^([a-zA-Z]{3,30}\s*)+$");
   name.addEventListener('blur', function() {validateInput(name,nameRegex)});
   
   let lastName = document.getElementsByName("nazwisko")[0];
   let lastNameRegex= new RegExp("^([a-zA-Z]{3,30}\s*)+$");
   lastName.addEventListener('blur', function() {validateInput(lastName,lastNameRegex)});
   
   let email = document.getElementsByName("e-mail")[0];
   let emailRegex = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
   email.addEventListener('blur', function() {validateInput(email,emailRegex)});
   
   
   let phone = document.getElementsByName("numer_telefonu")[0];
   let phoneRegex= /^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/;
   phone.addEventListener('blur', function() {validateInput(phone,phoneRegex)});

   
   function validateInput(inputElement, regex) {
      inputElement.classList.remove("invalid-input");
      if(!regex.test(inputElement.value)) { 
         incorrectInputValue(inputElement);
         return false;
      }
      return true;
   }
   
   function validateForm(){
      submitButton.classList.remove("invalid-input");
      validForm = true;
      accountNumber.forEach(element => 
        validForm&= validateInput(element, accountNumberRegex)
      );
      validForm&= validateInput(date, dateRegex);
      validForm&= validateInput(name, nameRegex);
      validForm&= validateInput(lastName, lastNameRegex);
      validForm&= validateInput(email, emailRegex);
      validForm&= validateInput(phone, phoneRegex);
      if (validForm) {
          document.getElementsByName("dane")[0].submit();
      } else {
         incorrectInputValue(submitButton);
         if (document.getElementById("response_table") != null)
         document.getElementById("response_table").style.display = "none";
      }
   }
   
   function incorrectInputValue(inputElement) {
      inputElement.classList.add("invalid-input");
   }
});