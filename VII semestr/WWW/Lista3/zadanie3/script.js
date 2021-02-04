document.addEventListener('DOMContentLoaded', function(){ 
   
   let loadingBoxes = document.getElementsByClassName('loading-box');
   for (i = 0; i < loadingBoxes.length; i++) {
      startMoving(loadingBoxes[i],i);
   }
   
   function startMoving(box,i) {
      let timeout = i*250;
      setTimeout(function(){moveBoxUp(box);},timeout);
   }
   
   function moveBoxUp(box) {
      box.style.top = -25+'px';
      setTimeout(function(){moveBoxDown(box);},1200);
   }
   
   function moveBoxDown(box) {
      box.style.top = 25+'px';
      setTimeout(function(){moveBoxUp(box);},1200);
   }


});


//setTimeout(checkTime, 1000/(1/framerate));