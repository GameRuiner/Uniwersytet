#[allow(dead_code)]
fn encode(msg: String, n: i32) -> Vec<i32> {
    let key = n.to_string()
                       .chars().map(|x| x.to_string().parse::<i32>().unwrap()).collect::<Vec<i32>>();

    msg.chars().enumerate()
               .map(|(i,c)| (c as i32 - 'a' as i32) + 1 + key[(i % key.len())] ).collect()
    
}



#[cfg(test)]
mod tests {
    use super::*;
    #[test]
    fn fixed_test1() {
        assert_eq!(encode("scout".to_string(), 1939), vec![20, 12, 18, 30, 21]);
    }

    #[test]
    fn fixed_test2() {
        assert_eq!(encode("masterpiece".to_string(), 1939), vec![14, 10, 22, 29, 6, 27, 19, 18, 6, 12, 8]);
    }

    #[test]
    fn fixed_test3() {
        assert_eq!(encode("master".to_string(), 1939), vec![14, 10, 22, 29, 6, 27]);
    }

    #[test]
    fn fixed_test4() {
        assert_eq!(encode("piece".to_string(), 1939), vec![17, 18, 8, 12, 6]);
    }

    #[test]
    fn fixed_test5() {
        assert_eq!(encode("s".to_string(), 1939), vec![20]);
    }
}