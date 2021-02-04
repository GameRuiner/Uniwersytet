#[allow(dead_code)]
fn dbl_linear(n: u32) -> u32{
    // your code
    let mut sequence = Vec::<u32>::new();
    sequence.push(1);


    for i in 0..n {
        let x = sequence[i as usize]*2+1;
        let y = sequence[i as usize]*3+1;
        match sequence.binary_search(&x) {
            Ok(_pos) => {} 
            Err(pos) => sequence.insert(pos, x),
        }
        match sequence.binary_search(&y) {
            Ok(_pos) => {}  
            Err(pos) => sequence.insert(pos, y),
        }
        
    }
    
    sequence[n as usize]

}

#[cfg(test)]
mod tests {
    use super::dbl_linear;
    fn testing(n: u32, exp: u32) -> () {
        assert_eq!(dbl_linear(n), exp)
    }
    
    #[test]
    fn test1() {
        testing(10, 22);
    }

    #[test]
    fn test2() {
        testing(20, 57);
    }

    #[test]
    fn test3() {
        testing(30, 91);
    }

    #[test]
    fn test4() {
        testing(50, 175);
    }

    #[test]
    fn test5() {
        testing(100, 447);
    }
}