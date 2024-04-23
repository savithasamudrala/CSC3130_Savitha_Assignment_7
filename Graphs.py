import networkx as nx
import matplotlib.pyplot as plt


def draw_graph(input_array):
    n = len(input_array)

    # Create directed graph
    G = nx.DiGraph()

    # Add nodes to the graph
    for i in range(n):
        G.add_node(input_array[i][0])

    # Add edges to the graph
    for i in range(n):
        vertex = input_array[i][0]
        index_left = (i - int(input_array[i][1]) + n) % n
        index_right = (i + int(input_array[i][1])) % n
        G.add_edge(vertex, input_array[index_left][0])
        G.add_edge(vertex, input_array[index_right][0])

    # Draw the graph
    pos = nx.circular_layout(G)
    nx.draw(G, pos, with_labels=True, node_size=1000, node_color='lightblue', font_size=12, font_weight='bold')
    plt.title('Directed Graph')
    plt.show()


# Sample input array
input_array = [("I", 2), ("A", 5), ("E", 4), ("F", 1), ("T", 2), ("S", 3)]
draw_graph(input_array)
