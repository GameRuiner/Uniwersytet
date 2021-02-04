#[allow(dead_code)]
fn descending_order(x: u64) -> u64 {
    let mut num_vec = Vec::<u64>::new();
    let mut num = x;

    loop {
        num_vec.push(num%10);
        num/= 10;
        if num==0 { break; };
    }
    
    num_vec.sort();

    num_vec.iter()
           .enumerate()
           .fold(0, |acc, (i,x)| acc + *x * (10 as u64).pow(i as u32))
}

#[test]
fn test1() {
    assert_eq!(descending_order(0), 0);
}

#[test]
fn test2() {
    assert_eq!(descending_order(1), 1);
}

#[test]
fn test3() {
    assert_eq!(descending_order(15), 51);
}

#[test]
fn test4() {
    assert_eq!(descending_order(1021), 2110);
}

#[test]
fn test5() {
    assert_eq!(descending_order(123456789), 987654321);
    assert_eq!(descending_order(145263), 654321);
    assert_eq!(descending_order(1254859723), 9875543221);
}