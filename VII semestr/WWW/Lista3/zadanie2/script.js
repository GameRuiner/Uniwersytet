// Porsche Cayenne 911
// Lada Priora Niva

document.addEventListener('DOMContentLoaded', function(){ 
   let brands = {"Ford": ["Model A","Model T"], "Fiat": ["Brava","Punto"]};
   
   let selectBrand = document.getElementById("brand");
   let selectModel = document.getElementById("model");
   let buttonAdd = document.getElementById("add_position");
   
   buttonAdd.addEventListener("click", addPosition);
   selectBrand.addEventListener("change", updateModel);
   
   updateBrand();
   
   function updateBrand() {
      let values = [];

      for (let i = 0; i < selectBrand.options.length; i++) {
         values.push(selectBrand.options[i].value);
      }
      
      for (brand in brands) {
         if (values.includes(brand)) continue;
         selectBrand.add(new Option(brand,brand));
      }
      
      updateModel()
   }
   
   
   function updateModel() {
      removeOptions(selectModel)
      
      for (model in brands[selectBrand.value]) {
         let optionModel = brands[selectBrand.value][model]
         selectModel.add(new Option(optionModel,optionModel));
      }
   }
   
   function addPosition() {
      let radioType = document.getElementsByName('rodzaj');
      let categoryValue;
      for (i = 0; i < radioType.length; i++) {
         if (radioType[i].checked) categoryValue=radioType[i].value;
      }
      
      let newPosition = document.getElementById("new_position").value;

      if (categoryValue == "marka") {
         if (!(newPosition in brands)) {
            brands[newPosition] = [];
            updateBrand();
         }
      } else {
         if (!(brands[selectBrand.value].includes(newPosition))) {
            brands[selectBrand.value].push(newPosition);
            updateModel();
         }
      }
   }
   
   function removeOptions(selectElement) {
      let i, L = selectElement.options.length - 1;
      for(i = L; i >= 0; i--) {
         selectElement.remove(i);
      }
   }


});