pub fn last_digit(str1: &str, str2: &str) -> i32 {
    
    if str2 == "0" {return 1};
    
    let len = str2.len();
    let final_numbers;
    if len >= 2 {
        final_numbers = str2[len-2..].parse::<i32>().unwrap();
    } else {
        final_numbers = str2.parse::<i32>().unwrap()
    }
    let n = str1.chars().last().unwrap().to_digit(10).unwrap() as i32;
    match n {
        0 => 0,
        1 => 1,
        5 => 5,
        6 => 6,
        _ => last_digit2(n, final_numbers),
    } 
  }


fn last_digit2(last_x: i32, last_of_y: i32) -> i32{
    let x_is_even = last_x % 2 == 0;
    let y_is_mult4 = last_of_y % 4 == 0;

    match (x_is_even,y_is_mult4) {
        (true,true) => 6,
        (false, true) => 1,
        _ => {
            let remain_y = last_of_y % 4;
            let n = last_digit2(last_x,4);
            n * last_x.pow(remain_y as u32) % 10 
        }
    }

}

#[test]
fn returns_expected1() {
  assert_eq!(last_digit("4", "1"), 4);
}

#[test]
fn returns_expected2() {
  assert_eq!(last_digit("4", "2"), 6);
}

#[test]
fn returns_expected3() {
  assert_eq!(last_digit("9", "7"), 9);
}

#[test]
fn returns_expected4() {
  assert_eq!(last_digit("10","10000000000"), 0);
}

#[test]
fn returns_expected5() {
  assert_eq!(last_digit("1606938044258990275541962092341162602522202993782792835301376","2037035976334486086268445688409378161051468393665936250636140449354381299763336706183397376"), 6);
}