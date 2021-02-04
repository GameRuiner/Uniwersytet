pub fn dont_give_me_five(start: isize, end: isize) -> isize {
    (start..end+1).filter(|num| no5_in_it(num.to_string())).count() as isize
    
}

fn no5_in_it(strng: String) -> bool {
    strng.chars()
    .into_iter()
    .fold(true, |acc, x| acc && x != '5')
}

#[test]
fn returns_expected1() {
    assert_eq!(dont_give_me_five(1, 9), 8);
}

#[test]
fn returns_expected2() {
    assert_eq!(dont_give_me_five(4, 17), 12);
}

#[test]
fn returns_expected3() {
    assert_eq!(dont_give_me_five(1, 5), 4);
}

#[test]
fn returns_expected4() {
    assert_eq!(dont_give_me_five(5, 10), 5);
}

#[test]
fn returns_expected5() {
    assert_eq!(dont_give_me_five(5, 15), 9);
}