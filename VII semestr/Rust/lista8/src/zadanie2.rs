#[allow(dead_code)]
fn dig_pow(n: i64, p: i32) -> i64 {  
    let num  = n.to_string().chars()
                 .map(|x| x.to_string().parse::<i64>().unwrap())
                 .enumerate()
                 .fold(0, |acc, (j, x)| acc+x.pow(p as u32 +j as u32));
    
    if num % n == 0 {
        num / n
    } else {
        -1
    }
}



#[cfg(test)]
    mod tests {
    use super::*;

    fn dotest(n: i64, p: i32, exp: i64) -> () {
        println!(" n: {:?};", n);
        println!("p: {:?};", p);
        let ans = dig_pow(n, p);
        println!(" actual:\n{:?};", ans);
        println!("expect:\n{:?};", exp);
        println!(" {};", ans == exp);
        assert_eq!(ans, exp);
        println!("{};", "-");
    }
    
    #[test]
    fn basic_test1() {
        dotest(89, 1, 1);
    }

    #[test]
    fn basic_test2() {
        dotest(92, 1, -1);
    }

    #[test]
    fn basic_test3() {
        dotest(46288, 3, 51); 
    }

    #[test]
    fn basic_test4() {
        dotest(695, 2, 2); 
    }

    #[test]
    fn basic_test5() {
        dotest(46288, 2, -1); 
    }
}