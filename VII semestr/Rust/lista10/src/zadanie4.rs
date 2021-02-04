#[allow(dead_code)]
fn john(n: i32) -> Vec<i32> {
    let mut days_kata = vec![0];
    let mut ann_days = vec![1];
    if n != 1 {
        for i in 1..n {
            let ann_index = days_kata[(i-1) as usize] ;
            if ann_index as usize >= ann_days.len() {
                ann_days.push(ann_index - days_kata[ann_days[(ann_index-1) as usize] as usize]);
            }
            days_kata.push(i - ann_days[ann_index as usize]);
        }
    }
    days_kata
}

#[allow(dead_code)]
fn ann(n: i32) -> Vec<i32> {
    let mut days_kata = vec![1];
    let mut john_days = vec![0];
    if n == 1 {
        return days_kata;
    }  else {
        for i in 1..n {
            let john_index = days_kata[(i-1) as usize] ;
            if john_index as usize >= john_days.len() {
                john_days.push(john_index - days_kata[john_days[(john_index-1) as usize] as usize]);
            }
            days_kata.push(i - john_days[john_index as usize]);
        }
        return days_kata;
    }
}

#[allow(dead_code)]
fn sum_john(n: i32) -> i32 {
    // your code
    john(n).iter().sum()
}

#[allow(dead_code)]
fn sum_ann(n: i32) -> i32 {
    // your code
    ann(n).iter().sum()
}


#[allow(dead_code)]
fn test_john(n: i32, exp: Vec<i32>) -> () {
    assert_eq!(john(n), exp)
}

#[allow(dead_code)]
fn test_ann(n: i32, exp: Vec<i32>) -> () {
    assert_eq!(ann(n), exp)
}

#[allow(dead_code)]
fn test_sum_john(n: i32, exp: i32) -> () {
    assert_eq!(sum_john(n), exp)
}

#[allow(dead_code)]
fn test_sum_ann(n: i32, exp: i32) -> () {
    assert_eq!(sum_ann(n), exp)
}

#[test]
fn test_test_john() {
    test_john(11, vec![0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6]);
    test_john(14, vec![0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8]);
}


#[test]
fn test_test_ann() {
    test_ann(6, vec![1, 1, 2, 2, 3, 3]);
    test_ann(15, vec![1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 8, 9]);
}
#[test]
fn test_test_sum_john() {
    test_sum_john(75, 1720);
    test_sum_john(78, 1861);
}
#[test]
fn test_test_sum_ann() {
    test_sum_ann(115, 4070);
    test_sum_ann(150, 6930);
}
