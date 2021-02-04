pub fn get_count(string: &str) -> usize {
    let mut vowels_count: usize = 0;

    let _n = string.chars().map(|ch| match ch.to_string().as_str() {
        "a" => vowels_count+=1,
        "e" => vowels_count+=1,
        "o" => vowels_count+=1, 
        "u" => vowels_count+=1,
        "i" => vowels_count+=1,
        _ => (),
    }).collect::<Vec<_>>();
    vowels_count
  }



#[test]
fn my_test1() {
  assert_eq!(get_count("abracadabra"), 5);
}

#[test]
fn my_test2() {
  assert_eq!(get_count("a"), 1);
}

#[test]
fn my_test3() {
  assert_eq!(get_count("tree"), 2);
}

#[test]
fn my_test4() {
  assert_eq!(get_count("ice cream"), 4);
}

#[test]
fn my_test5() {
  assert_eq!(get_count("iphone"), 3);
}