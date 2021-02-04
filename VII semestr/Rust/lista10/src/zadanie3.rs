#[allow(dead_code)]
fn part_list(arr: Vec<&str>) -> String {
    let mut res = "".to_string();
    let mut divide = 1;
    for _j in 0..arr.len()-1 {
        res.push('(');
        res.push_str(arr[0]);
        for i in 1..arr.len() {
            if i == divide {
                res.push(',');
            }
            res.push(' ');
            res.push_str(arr[i]);
        }
        divide+=1;
        res.push(')');
    }
    res.to_string()
}


#[cfg(test)]
    mod tests {
    use super::*;

    fn dotest(arr: Vec<&str>, exp: &str) -> () {
        println!("arr: {:?}", arr);
        let ans = part_list(arr);
        println!("actual:\n{}", ans);
        println!("expect:\n{}", exp);
        println!("{}", ans == exp);
        assert_eq!(ans, exp);
        println!("{}", "-");
    }

    #[test]
    fn basis_tests() {
        dotest(vec!["I", "wish", "I", "hadn't", "come"],
                "(I, wish I hadn't come)(I wish, I hadn't come)(I wish I, hadn't come)(I wish I hadn't, come)");
        dotest(vec!["cdIw", "tzIy", "xDu", "rThG"], 
            "(cdIw, tzIy xDu rThG)(cdIw tzIy, xDu rThG)(cdIw tzIy xDu, rThG)");
        
    }
}
