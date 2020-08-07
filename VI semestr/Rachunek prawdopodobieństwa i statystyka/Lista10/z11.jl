Vx = Float64[54.3, 61.8, 72.4, 88.7, 118.6, 194.0]
Py = Float64[61.2, 49.5, 37.6, 28.4, 19.2, 10.1]
function test()
y_avg = 34.5
x_avg = 98.467
y_sum = 0
x_sum = 0
for i in 1:6
   x_sum+=Vx[i]
   y_sum+=Py[i]
end
println(x_sum/6," ", y_sum/6)
end
test()