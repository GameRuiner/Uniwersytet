include("newton.jl")

function aux(x, r, nmax = 15)
	n = 0
	while nmax >= n
		xn = x*(2 - r*x)
		println(xn)
		x = xn
		n+=1
		end
	println("1/r = ", 1/r)
end

aux(0.01,5,20)
aux(0.01,0.002,20)

aux(0.01,sqrt(5),20)
aux(0.01,sqrt(0.002),20)