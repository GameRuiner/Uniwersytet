#[allow(dead_code)]
fn print(n: i32) -> Option<String> {
    if n < 1 || n % 2 == 0 {
        return None;
    }
    let mut stars = 1;
    let mut spaces = (n-1)/2;
    let mut res = "".to_string();
    let mut mul = 1;
    for i in 0..n {
        let mut line = "".to_string();
        for _j in 0..spaces {
            line.push(' ');
        }
        for _j in 0..stars {
            line.push('*');
        }
        line.push('\n');
        res.push_str(&line);
        stars += 2*mul;
        spaces -= mul;
        if i+1 == (n-1)/2 {
            mul *= -1;
        }
    } 
    Some(res)
}


#[test]
fn basic_test1() {
  assert_eq!(print(3), Some(" *\n***\n *\n".to_string()) );
}

#[test]
fn basic_test2() {
  assert_eq!(print(5), Some("  *\n ***\n*****\n ***\n  *\n".to_string()) );
}

#[test]
fn basic_test3() {
  assert_eq!(print(-3),None);
}

#[test]
fn basic_test4() {
  assert_eq!(print(2),None);
  assert_eq!(print(0),None);
}

#[test]
fn basic_test5() {
  assert_eq!(print(1), Some("*\n".to_string()) );
}