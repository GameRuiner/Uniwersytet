use std::collections::BTreeMap;

#[allow(dead_code)]
fn letter_frequency(input: &str) -> BTreeMap<char, i32> {
    let mut answer = BTreeMap::<char, i32>::new();
    let mut sorted_v = input.to_lowercase().chars()
                                     .filter(|x| x.is_alphabetic())
                                     .collect::<Vec<_>>();
    sorted_v.sort();
    for ch in  sorted_v{
        // if answer.contains_key(&ch) {
        //     //let count = *answer.get(&ch).unwrap();
        //     answer.remove(&ch);
        //     answer.insert(ch, count+1);   
        // } else {
        //     answer.insert(ch, 1);
        // }
        *answer.entry(ch).or_insert(0) += 1;
    }
    
    answer
}


// Rust test example:
// TODO: replace with your own tests (TDD), these are just how-to examples.
// See: https://doc.rust-lang.org/book/testing.html

#[cfg(test)]
mod tests {
    use std::collections::BTreeMap;
    use super::letter_frequency;
    
    #[test]
    fn simpleword() {
        let answer: BTreeMap<char, i32> =
        [('a', 2),
         ('c', 1),
         ('l', 1),
         ('t', 1),
         ('u', 1)]
         .iter().cloned().collect();
         
      assert_eq!(letter_frequency("actual"), answer);
    }
    
    #[test]
    fn sequence() {
        let answer: BTreeMap<char, i32> =
        [('a', 3),
         ('b', 2),
         ('f', 1),
         ('p', 1),
         ('s', 1),
         ('t', 2),
         ('u', 1),
         ('x', 5)]
         .iter().cloned().collect();
         
      assert_eq!(letter_frequency("AaabBF UttsP xxxxx"), answer);
    }
}