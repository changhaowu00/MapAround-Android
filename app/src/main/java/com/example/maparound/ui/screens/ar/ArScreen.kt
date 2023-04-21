package com.example.maparound.ui.screens.ar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.node.ArNode
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

@Composable
fun ArScreen(){
    val nodes = remember { mutableStateListOf<ArNode>() }
    val context = LocalContext.current

    data class Model(
        val fileLocation: String,
        val scaleUnits: Float? = null,
        val placementMode: PlacementMode = PlacementMode.BEST_AVAILABLE,
        val applyPoseRotation: Boolean = true
    )
    val models = listOf(
        Model("models/spiderbot.glb"),
        Model(
            fileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/Tiger/model.glb",
            // Display the Tiger with a size of 3 m long
            scaleUnits = 2.5f,
            placementMode = PlacementMode.BEST_AVAILABLE,
            applyPoseRotation = false
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
            placementMode = PlacementMode.INSTANT,
            scaleUnits = 0.5f
        ),
        Model(
            fileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/GiantPanda/model.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL,
            // Display the Tiger with a size of 1.5 m height
            scaleUnits = 1.5f
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/Spoons.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL_AND_VERTICAL,
            // Keep original model size
            scaleUnits = null
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/Halloween.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL,
            scaleUnits = 2.5f
        ),
    )

    """
    val model = ArModelNode(
        placementMode = PlacementMode.BEST_AVAILABLE,
        instantAnchor = false,
        hitPosition = Position(0.0f, 0.0f, -2.0f),
        followHitPosition = true,
    ).apply {
        loadModelGlbAsync(
            context = context,
            glbFileLocation = "models/{monster?.monsterModel}",
            autoAnimate = true,
            centerOrigin = Position(x = 0.0f, y = -1.0f, z = 0.0f),
        )
    }
    
    """

    Box(modifier = Modifier.fillMaxSize()) {
        ARScene(
            modifier = Modifier.fillMaxSize(),
            nodes = nodes,
            planeRenderer = true,
            onCreate = { arSceneView ->
                // Apply your configuration
            },
            onSessionCreate = { session ->
                // Configure the ARCore session
            },
            onFrame = { arFrame ->
                // Retrieve ARCore frame update
            },
            onTap = { hitResult ->
                // User tapped in the AR view
            }
        )
    }
}

@Preview
@Composable
fun ArScreenPreview(){

}