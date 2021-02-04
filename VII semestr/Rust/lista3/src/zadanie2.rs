#[allow(dead_code)]
struct Cipher {
    map1: String,
    map2 : String,

}

#[allow(dead_code)]
  impl Cipher {
    fn new(map1: &str, map2: &str) -> Cipher {
      Cipher {map1: map1.to_string(), map2: map2.to_string()}
    }
    
    fn encode(&self, string: &str) -> String {
      
      let mut res = "".to_string();
      
      for ch in string.chars() {
        if self.map1.contains(ch) {
          for (x,y) in 
                self.map1.chars().zip(self.map2.chars()) {
            if x == ch {
              res.push(y);
            };
          };
        } else {
           res.push(ch);
        }}

      res
      
    }
    
    fn decode(&self, string: &str) -> String {
      let mut res = "".to_string();
      
      for ch in string.chars() {
        if self.map2.contains(ch) {
          for (x,y) in 
                self.map2.chars().zip(self.map1.chars()) {
            if x == ch {
              res.push(y);
            };
          };
        } else {
           res.push(ch);
        }}

      res

    }
  
}




#[test]
fn example1() {
  let map1 = "abcdefghijklmnopqrstuvwxyz";
  let map2 = "etaoinshrdlucmfwypvbgkjqxz";

  let cipher = Cipher::new(map1, map2);
  
  assert_eq!(cipher.encode("abc"), "eta");
}

#[test]
fn example2() {
  let map1 = "abcdefghijklmnopqrstuvwxyz";
  let map2 = "etaoinshrdlucmfwypvbgkjqxz";

  let cipher = Cipher::new(map1, map2);
  
  assert_eq!(cipher.encode("xyz"), "qxz");
}

#[test]
fn example3() {
  let map1 = "abcdefghijklmnopqrstuvwxyz";
  let map2 = "etaoinshrdlucmfwypvbgkjqxz";

  let cipher = Cipher::new(map1, map2);
  
  assert_eq!(cipher.decode("eirfg"), "aeiou");
}

#[test]
fn example4() {
  let map1 = "abcdefghijklmnopqrstuvwxyz";
  let map2 = "etaoinshrdlucmfwypvbgkjqxz";

  let cipher = Cipher::new(map1, map2);
  
  assert_eq!(cipher.decode("erlang"), "aikcfu");
}

#[test]
fn example5() {
  let map1 = "abcdefghijklmnopqrstuvwxyz";
  let map2 = "tyioenshrdlucmfwapvbgkjqxz";

  let cipher = Cipher::new(map1, map2);
  
  assert_eq!(cipher.encode("abc"), "tyi");
}