$(function(){
   
   let adverbs = "slowly quietly badly beautifully dangerously carefully easily healthily well fast hard".split(' ');
   let verbs = "eating speaking cooking starting doing staying fixing trying".split(' ');
   let adjectives = "agile amazing awful beautiful brilliant clean clever direct drunk eager friendly".split(' ');
   let nouns = "actor adventurer aggressor alcoholic amateur anarchist angel animal artist atheist".split(' ');
   let words=[adverbs,verbs,adjectives,nouns];
   
   $("#content").children().last().children().first().children().each(function(index) {
      let tedContent = "";
      for (word of words[index]) {
         tedContent+=word+"<br>";
      }
      $( this).html(tedContent);
   }); 
   
   $('#generate').on('click', function () {
     
      let phrase = "";
      for (i = 0; i< words.length; i++) {
         let word = words[i][Math.floor(Math.random() * words[i].length)];
         phrase+=" "+word;
      }
      
      console.log(phrase);
      $('#bullshit').val(phrase);
      
   });
   
});