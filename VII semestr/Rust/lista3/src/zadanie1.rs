#[allow(dead_code)]
fn good_vs_evil(good: &str, evil: &str) -> String {

    let good_worth = [1,2,3,3,4,10];
    let evil_worth = [1,2,2,2,3,5,10];

    let good_strength = aux(&good_worth,
                         good);

    let evil_strength = aux(&evil_worth,
                         evil);

    fn aux(worth:&[i32] , side:&str) -> i32 {

        let count:Vec<i32> = side.split(" ").map(|ch| ch.parse().unwrap()).collect();
        let mut sum = 0;
        for i in 0..worth.len() {
            sum+=worth[i]*count[i];
        }
        sum
    }

    if evil_strength < good_strength {
        "Battle Result: Good triumphs over Evil".to_string()
    } else if evil_strength > good_strength {
        "Battle Result: Evil eradicates all trace of Good".to_string()
    } else{
        "Battle Result: No victor on this battle field".to_string()
    }

    

  }


  #[test]
fn returns_expected1() {
    assert_eq!(good_vs_evil("0 0 0 0 0 10", "0 0 0 0 0 0 0"), "Battle Result: Good triumphs over Evil");
}

#[test]
fn returns_expected2() {
    assert_eq!(good_vs_evil("0 0 0 0 0 0", "0 0 0 0 0 0 10"), "Battle Result: Evil eradicates all trace of Good");
}

#[test]
fn returns_expected3() {
    assert_eq!(good_vs_evil("0 0 0 0 0 10", "0 0 0 0 0 0 10"), "Battle Result: No victor on this battle field");
}

#[test]
fn returns_expected4() {
    assert_eq!(good_vs_evil("0 0 0 0 0 1", "0 1 1 0 0 0 0"), "Battle Result: Good triumphs over Evil");
}

#[test]
fn returns_expected5() {
    assert_eq!(good_vs_evil("1 1 1 0 0 0", "0 0 0 0 0 0 1"), "Battle Result: Evil eradicates all trace of Good");
}