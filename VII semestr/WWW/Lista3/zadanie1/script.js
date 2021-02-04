document.addEventListener('DOMContentLoaded', function(){
   
   let submitButton = document.getElementById("submit_button");
   submitButton.addEventListener('click', validateForm);
   
   let accountNumber = document.getElementById("account_number");
   let accountNumberRegex = new RegExp("^[0-9]{26}$");
   accountNumber.addEventListener('blur', function() {validateInput(accountNumber, accountNumberRegex)});   
   
   let pesel = document.getElementById("pesel");
   let peselRegex = /^[0-9]{11}$/;
   pesel.addEventListener('blur', function() {validateInput(pesel,peselRegex)});
   
   let date = document.getElementById("date");
   let dateExtracted = (null,null,null);
   let dateRegex = /^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)([0-9]{2})$/;
   date.addEventListener('blur', function() {validateInput(date,dateRegex)});
   
   let email = document.getElementById("email");
   let emailRegex = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
   email.addEventListener('blur', function() {validateInput(email,emailRegex)});
   
   function validateInput(inputElement, regex) {
      inputElement.classList.remove("invalid-input");
      
      if(!regex.test(inputElement.value)) { 
         incorrectInputValue(inputElement); 
      } else if (inputElement.name == 'date') {
         extractDate(inputElement.value.match(regex));
      }
   }
   
   function extractDate(match) {
      peselRegex = new RegExp(`^${match[4]}${match[2]}${match[1]}[0-9]{5}$`);
      validateInput(pesel,peselRegex);
   }  
   
   
   function validateForm(){
      validateInput(accountNumber, accountNumberRegex);
      validateInput(pesel, peselRegex);
      validateInput(date, dateRegex);
      validateInput(email, emailRegex);
   }
   
   function incorrectInputValue(inputElement) {
      inputElement.classList.add("invalid-input");
   }
});