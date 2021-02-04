#[allow(dead_code)]
pub fn find_digit(num: i32, nth: i32) -> i32 {
    if nth < 1 {
        return -1;
    };
    let num_str = num.abs().to_string();
    let nthlr = (num_str.len() as i32 - nth) as usize;
    let num_char = num_str.chars().nth(nthlr).unwrap_or('0');
    num_char.to_string().parse::<i32>().unwrap()
}

#[test]
fn test1() {
  assert_eq!(find_digit(5673, 4), 5);

}

#[test]
fn test2() {
    assert_eq!(find_digit(129, 2), 2);
}

#[test]
fn test3() {
    assert_eq!(find_digit(-2825, 3), 8);
}

#[test]
fn test4() {
    assert_eq!(find_digit(-456, 4), 0);
}

#[test]
fn test5() {
    assert_eq!(find_digit(0, 20), 0);
}