pub fn summy(strng: &str) -> i32 {
    strng.split(" ")
    .into_iter()
    .fold(0, |acc, x| acc + x.parse::<i32>().unwrap())
}

#[test]
fn sample_tests1() {
    assert_eq!(summy("1 2 3"), 6);
}

#[test]
fn sample_tests2() {
    assert_eq!(summy("1 2 3 4"), 10);
}

#[test]
fn sample_tests3() {
    assert_eq!(summy("1 2 3 4 5"), 15);
}

#[test]
fn sample_tests4() {
    assert_eq!(summy("10 10"), 20);
}

#[test]
fn sample_tests5() {
    assert_eq!(summy("0 0"), 0);
}
