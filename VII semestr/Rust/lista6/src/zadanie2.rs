mod solution {

    #[allow(dead_code)]
    pub fn range_extraction(a: &[i32]) -> String {
        // Your solution here
        let mut res = "".to_string();
        let mut last_number = a[0];
        let mut second_of_sequence_num = a[0];
        let mut first  = true;
        let mut first_of_sequence = true;
        for i in a {
            if first {
                res.push_str(&format!("{}",*(i)));
                first = false;
            } else {
                if *i == last_number+1 {
                    if first_of_sequence {
                        //res.push_str("-");
                        second_of_sequence_num = *i;
                        first_of_sequence = false;
                    } 
                } else {
                    if !first_of_sequence {
                        if second_of_sequence_num == last_number {
                            res.push_str(&format!(",{}",last_number));
                        } else {
                            res.push_str(&format!("-{}",last_number));
                        }
                        res.push_str(&format!(",{}",*(i)));
                        first_of_sequence = true;
                    } else {
                        res.push_str(&format!(",{}",*(i)));
                    }
                }
                last_number = *i;
            }
           // res.push_str(&format!("{},",*(i)));
        }

        if !first_of_sequence {
            if second_of_sequence_num == last_number {
                res.push_str(&format!(",{}",last_number));
            } else {
                res.push_str(&format!("-{}",last_number));
            }
        }
        println!("{}",res);
        res
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example1() {
        assert_eq!("-6,-3-1,3-5,7-11,14,15,17-20", solution::range_extraction(&[-6,-3,-2,-1,0,1,3,4,5,7,8,9,10,11,14,15,17,18,19,20]));	
    }

    #[test]
    fn example2() {	
        assert_eq!("-3--1,2,10,15,16,18-20", solution::range_extraction(&[-3,-2,-1,2,10,15,16,18,19,20]));
    }

    #[test]
    fn example3() {	
        assert_eq!("-3--1,2,10,15,16,18,19", solution::range_extraction(&[-3,-2,-1,2,10,15,16,18,19]));
    }

    #[test]
    fn example4() {	
        assert_eq!("-3-0", solution::range_extraction(&[-3,-2,-1,0]));
    }

    #[test]
    fn example5() {	
        assert_eq!("10,15,16,18-20", solution::range_extraction(&[10,15,16,18,19,20]));
    }
}
