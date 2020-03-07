include("newton.jl")

function aux(x, r, nmax = 15)
	n = 0
	while nmax >= n
		xn = 0.5*(x+r*x^3)
		println(xn)
		x = xn
		n+=1
		end
	println("1/r = ", 1/(r^2))
end

aux(1,5,20)
aux(1,0.002,20)
