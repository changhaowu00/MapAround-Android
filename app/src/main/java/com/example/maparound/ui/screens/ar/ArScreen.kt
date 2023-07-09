package com.example.maparound.ui.screens.ar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

@Composable
fun ArScreen(){
    lateinit var sceneView: ArSceneView
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

    var index = 1
    var firstTap = true

    val model1 = ArModelNode(
        placementMode = PlacementMode.BEST_AVAILABLE,
        instantAnchor = false,
        hitPosition = Position(0.0f, 0.0f, -2.0f),
        followHitPosition = true,
    ).apply {
        loadModelGlbAsync(
            context = context,
            glbFileLocation = "https://github.com/changhaowu00/ArModels/raw/20d0dee32e5436d28954732a4621f53fe845eae7/c3m.glb",
            autoAnimate = true,
            scaleToUnits = 1f,
            // Place the model origin at the bottom center
            centerOrigin = Position(y = -1.0f)
        )
    }

    val model2 = ArModelNode(
        placementMode = PlacementMode.DEPTH,
        instantAnchor = false,
        hitPosition = Position(0.0f, 0.0f, -2.0f),
        followHitPosition = true,
    ).apply {
        loadModelGlbAsync(
            context = context,
            glbFileLocation = "https://github.com/changhaowu00/ArModels/raw/20d0dee32e5436d28954732a4621f53fe845eae7/c3m.glb",
            autoAnimate = true,
            scaleToUnits = 1f,
            // Place the model origin at the bottom center
            centerOrigin = Position(y = -1.0f)
        )
    }

    val model3 = ArModelNode(
        placementMode = PlacementMode.BEST_AVAILABLE,
        instantAnchor = false,
        hitPosition = Position(0.0f, 0.0f, -2.0f),
        followHitPosition = true,
    ).apply {
        loadModelGlbAsync(
            context = context,
            glbFileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/GiantPanda/model.glb",
            autoAnimate = true,
            scaleToUnits = 1.5f,
            // Place the model origin at the bottom center
            centerOrigin = Position(y = -1.0f)
        )
    }

    val model4 = ArModelNode(
        placementMode = PlacementMode.BEST_AVAILABLE,
        instantAnchor = false,
        hitPosition = Position(0.0f, 0.0f, -2.0f),
        followHitPosition = true,
    ).apply {
        loadModelGlbAsync(
            context = context,
            glbFileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/GiantPanda/model.glb",
            autoAnimate = true,
            scaleToUnits = 1.0f,
            // Place the model origin at the bottom center
            centerOrigin = Position(y = -1.0f)
        )
    }

    val model5 = ArModelNode(
        placementMode = PlacementMode.BEST_AVAILABLE,
        instantAnchor = false,
        hitPosition = Position(0.0f, 0.0f, -2.0f),
        followHitPosition = true,
    ).apply {
        loadModelGlbAsync(
            context = context,
            glbFileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/GiantPanda/model.glb",
            autoAnimate = true,
            scaleToUnits = 0.6f,
            // Place the model origin at the bottom center
            centerOrigin = Position(y = -1.0f)
        )
    }

    val model6 = ArModelNode(
        placementMode = PlacementMode.BEST_AVAILABLE,
        instantAnchor = false,
        hitPosition = Position(0.0f, 0.0f, -2.0f),
        followHitPosition = true,
    ).apply {
        loadModelGlbAsync(
            context = context,
            glbFileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/Tiger/model.glb",
            autoAnimate = true,
            scaleToUnits = 2.5f,
            // Place the model origin at the bottom center
            centerOrigin = Position(y = -1.0f)
        )
    }



    Box(modifier = Modifier.fillMaxSize()) {
        ARScene(
            modifier = Modifier.fillMaxSize(),
            nodes = nodes,
            planeRenderer = true,
            onCreate = { arSceneView ->
                // Apply your configuration
                sceneView = arSceneView


            },
            onSessionCreate = { session ->
                // Configure the ARCore session
            },
            onFrame = { arFrame ->
                // Retrieve ARCore frame update
            },
            onTap = { hitResult ->
                // User tapped in the AR view
                /*
                sceneView.addChild(model)
                model.playAnimation(2)
                model.playAnimation(1)
                model.playAnimation(0)
                model.anchor()*/
                if (firstTap && index == 1){
                    sceneView.addChild(model1)
                    firstTap = false
                }
                else if (firstTap==false && index == 1){
                    model1.anchor()
                    firstTap = true
                    index++
                }
                else if (firstTap && index == 2){
                    sceneView.addChild(model2)
                    model2.playAnimation(2)
                    model2.playAnimation(1)
                    model2.playAnimation(0)
                    firstTap = false
                }
                else if (firstTap==false && index == 2){
                    model2.anchor()
                    firstTap = true
                    index++
                }
                else{
                    sceneView.planeRenderer.isVisible = false
                }
                """
                else if (firstTap && index == 3){
                    sceneView.addChild(model3)
                    firstTap = false
                }
                else if (firstTap==false && index == 3){
                    model3.anchor()
                    firstTap = true
                    index++
                }
                else if (firstTap && index == 4){
                    sceneView.addChild(model4)
                    firstTap = false
                }
                else if (firstTap==false && index == 4){
                    model4.anchor()
                    firstTap = true
                    index++
                }
                else if (firstTap && index == 5){
                    sceneView.addChild(model5)
                    firstTap = false
                }
                else if (firstTap==false && index == 5){
                    model5.anchor()
                    firstTap = true
                    index++
                }
                else if (firstTap && index == 6){
                    sceneView.addChild(model6)
                    firstTap = false
                }
                else if (firstTap==false && index == 6){
                    model6.anchor()
                    firstTap = true
                    index++
                }"""

                //model2.setCastShadows(false)
                //model2.setReceiveShadows(false)
                //model2.setScreenSpaceContactShadows(false)
            }
        )
    }
}

@Preview
@Composable
fun ArScreenPreview(){

}