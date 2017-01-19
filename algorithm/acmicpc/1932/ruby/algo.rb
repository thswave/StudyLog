n = gets.to_i 

input = []

1.upto(n) do |i|  
    line = gets 
    input << line.split(" ").map { |e| e.to_i }
end

if n == 1 
    puts input[0].max
end

1.upto(n-1) do |i|  

    pre_list = input[ i -1]
    cur_list = input[ i ]
    
    last_index = cur_list.size - 1
    0.upto(last_index) do | j | 
        if j == 0 
            cur_list[0] = pre_list[0] + cur_list[0]
        elsif j == last_index
            cur_list[ j ] = pre_list[j - 1] + cur_list[ j ]
        else 
            cur_list[ j ] = [ (pre_list[ j  - 1]  + cur_list[ j ] ),  (pre_list[ j  ]  + cur_list[ j ] )  ].max
        end
    end
    
end

puts input[n-1].max