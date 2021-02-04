pub fn longest(a1: &str, a2: &str) -> String {
    aux(&(format!("{}{}", a1, a2)))
}

pub fn aux(strng: &str) -> String {
    let mut vec = strng
             .chars()
             .collect::<Vec<char>>();
    vec.sort(); 
    vec.dedup();  
    vec.iter().collect::<String>()
}




fn testing(s1: &str, s2: &str, exp: &str) -> () {
    println!("s1:{:?} s2:{:?}", s1, s2);
    println!("{:?} {:?}", longest(s1, s2), exp);
    println!("{}", longest(s1, s2) == exp);
    assert_eq!(&longest(s1, s2), exp)
}

#[test]
fn basic_test1() {
    testing("aretheyhere", "yestheyarehere", "aehrsty");
    
}

#[test]
fn basic_test2() {
    testing("loopingisfunbutdangerous", "lessdangerousthancoding", "abcdefghilnoprstu");
    
}

#[test]
fn basic_test3() {
    testing("a", "b", "ab");
    
}

#[test]
fn basic_test4() {
    testing("aretheyherex", "yestheyarehere", "aehrstxy");
    
}

#[test]
fn basic_test5() {
    testing("aret", "y", "aerty");
    
}