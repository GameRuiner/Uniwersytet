pub fn camel_case(str: &str) -> String {
  str
         .split(" ")
         .map(|word| word_to_upper_case(word))
         .collect::<Vec<String>>()
         .join("")
         .to_string()
}

fn word_to_upper_case (s: &str) -> String {
  let mut c = s.chars();
  match c.next() {
      None => String::new(),
      Some(f) => f.to_uppercase().chain(c).collect(),
  }
}


// Rust tests
#[test]
fn sample_test1() {
  assert_eq!(camel_case("test case"), "TestCase");
}

#[test]
fn sample_test2() {
  assert_eq!(camel_case("camel case method"), "CamelCaseMethod");
}

#[test]
fn sample_test3() {
  assert_eq!(camel_case("say hello "), "SayHello");
}

#[test]
fn sample_test4() {
  assert_eq!(camel_case(" camel case word"), "CamelCaseWord");
}
#[test]
fn sample_test5() {
  assert_eq!(camel_case(""), "");
}
