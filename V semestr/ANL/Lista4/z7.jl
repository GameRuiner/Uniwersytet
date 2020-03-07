include("newton.jl")

function aux(x, r, nmax = 15)
	n = 0
	while nmax >= n
		xn = 0.5*(x+r/x)
		#xn = 0.5*(3*x-r/x)
		println(xn)
		x = xn
		n+=1
		end
	println("1/r = ", sqrt(r))
end

aux(10,10,15)
aux(1,0.004,20)