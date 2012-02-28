package com.tinkerpop.blueprints.pgm.util;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.impls.dex.DexGraph;
import com.tinkerpop.blueprints.pgm.impls.ig.IGGraph;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;
import com.tinkerpop.blueprints.pgm.impls.rexster.RexsterGraph;
import com.tinkerpop.blueprints.pgm.impls.sail.impls.MemoryStoreSailGraph;
import com.tinkerpop.blueprints.pgm.impls.sail.impls.NativeStoreSailGraph;
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraph;

/**
 * Generic Connection API for creating a BluePrint Graph
 * 
 */
public class GraphConnect {

	public static Graph openGraph(final String databasetype, String path) {
		if (databasetype.equalsIgnoreCase("Rexster")
				|| databasetype.equalsIgnoreCase("RexsterGraph")) {
			return new RexsterGraph(path);
		} else if (databasetype.equalsIgnoreCase("OrientDB")
				|| databasetype.equalsIgnoreCase("OrientGraph")) {
			if (!path.startsWith("local:") && !path.startsWith("memory:")
					&& !path.startsWith("remote:")) {
				path = "local:" + path; // default to local
			}
			return new OrientGraph(path);
		} else if (databasetype.equalsIgnoreCase("Neo4j")
				|| databasetype.equalsIgnoreCase("Neo4jGraph")) {
			return new Neo4jGraph(path);
		} else if (databasetype.equalsIgnoreCase("Dex")
				|| databasetype.equalsIgnoreCase("DexGraph")) {
			return new DexGraph(path);
		} else if (databasetype.equalsIgnoreCase("InfiniteGraph")
				|| databasetype.equalsIgnoreCase("IGGraph")) {
			return new IGGraph(path);
		} else if (databasetype.equalsIgnoreCase("TinkerGraph")
				|| databasetype.equalsIgnoreCase("TinkerPop")) {
			if (path == null || path.length() == 0) {
				return new TinkerGraph();
			}
			return new TinkerGraph(path);
		} else if (databasetype.equalsIgnoreCase("Sail")
				|| databasetype.equalsIgnoreCase("SailGraph")) {
			if (path == null || path.length() == 0) {
				return new MemoryStoreSailGraph();
			}
			return new NativeStoreSailGraph(path);
		}
		return null;
	}

	public static Graph openGraph() {
		return new TinkerGraph();
	}

}